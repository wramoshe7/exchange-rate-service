package pe.com.bank.bs.handler;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import pe.com.bank.bs.model.dto.ApiResponse;
import pe.com.bank.bs.model.dto.LoginRequest;
import pe.com.bank.bs.model.dto.LoginResponse;
import pe.com.bank.bs.model.UserExchange;
import pe.com.bank.bs.repository.UserRepository;
import reactor.core.publisher.Mono;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
@AllArgsConstructor
public class AuthHandler {

    private BCryptPasswordEncoder passwordEncoder;
    private TokenProvider tokenProvider;
    private UserRepository userRepository;

    public Mono<ServerResponse> login(ServerRequest request) {
        Mono<LoginRequest> loginRequest = request.bodyToMono(LoginRequest.class);
        return loginRequest.flatMap(login -> userRepository.getUserByUsername(login.getUsername())
            .flatMap(user -> {
                if (passwordEncoder.matches(login.getPassword(), user.getPassword())) {
                    return ServerResponse.ok()
                            .contentType(APPLICATION_JSON)
                            .body(BodyInserters.fromObject(new LoginResponse(tokenProvider.generateToken(user))));
                } else {
                    return ServerResponse.badRequest().body(BodyInserters.fromObject(new ApiResponse(400, "Invalid credentials", null)));
                }
            }).switchIfEmpty(ServerResponse.badRequest().body(BodyInserters.fromObject(new ApiResponse(400, "User does not exist", null)))));
    }

    public Mono<ServerResponse> signUp(ServerRequest request) {
        Mono<UserExchange> userMono = request.bodyToMono(UserExchange.class);
        return userMono.map(user -> {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return user;
        }).flatMap(user -> userRepository.getUserByUsername(user.getUsername())
                .flatMap(dbUser -> ServerResponse.badRequest()
                        .body(BodyInserters.fromObject(new ApiResponse(400, "User already exist", null))))
                .switchIfEmpty(userRepository.save(user)
                        .flatMap(savedUser -> ServerResponse.ok()
                                .contentType(APPLICATION_JSON)
                                .body(BodyInserters.fromObject(savedUser)))));
    }
}
