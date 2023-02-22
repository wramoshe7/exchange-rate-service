package pe.com.bank.bs.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import pe.com.bank.bs.model.ExchangeRate;
import reactor.core.publisher.Mono;

@Repository
public interface ExchangeRepository extends R2dbcRepository<ExchangeRate,Integer> {

    Mono<ExchangeRate> getExchangeRateBySourceCurrencyAndTargetCurrency(String sourceCurrency,
                                                                        String targetCurrency);

}