package pe.com.bank.bs.handler;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import pe.com.bank.bs.model.UserExchange;
import pe.com.bank.bs.repository.UserRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
@AllArgsConstructor
public class UserHandler {

    private UserRepository userRepository;

    public Mono<ServerResponse> createUser(ServerRequest request) {
        Mono<UserExchange> userMono = request.bodyToMono(UserExchange.class).flatMap(user -> userRepository.save(user));
        return ServerResponse.ok().contentType(APPLICATION_JSON).body(userMono, UserExchange.class);
    }

    public Mono<ServerResponse> listUser(ServerRequest request) {
        Flux<UserExchange> user = userRepository.findAll();
        return ServerResponse.ok().contentType(APPLICATION_JSON).body(user, UserExchange.class);
    }

    public Mono<ServerResponse> getUserById(ServerRequest request) {
        Integer userId = Integer.valueOf(request.pathVariable("userId"));
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        Mono<UserExchange> userMono = userRepository.findById(userId);
        return userMono.flatMap(user -> ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(BodyInserters.fromObject(user)))
                .switchIfEmpty(notFound);
    }

    public Mono<ServerResponse> deleteUser(ServerRequest request) {
        Integer userId = Integer.valueOf(request.pathVariable("userId"));
        userRepository.deleteById(userId);
        return ServerResponse.ok().build();
    }

}
