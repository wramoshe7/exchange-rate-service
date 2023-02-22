package pe.com.bank.bs.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import pe.com.bank.bs.model.UserExchange;
import reactor.core.publisher.Mono;

public interface UserRepository extends R2dbcRepository<UserExchange, Integer> {

    Mono<UserExchange> getUserByUsername(String username);
}
