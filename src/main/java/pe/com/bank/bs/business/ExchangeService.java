package pe.com.bank.bs.business;

import pe.com.bank.bs.model.ExchangeRate;
import pe.com.bank.bs.model.ExchangeResponse;
import reactor.core.publisher.Mono;

public interface ExchangeService {

    Mono<ExchangeRate> update(int id, ExchangeRate exchangeRate);
    Mono<ExchangeResponse> convert(Double amount, String origenCurrency, String destinationCurrency);
}