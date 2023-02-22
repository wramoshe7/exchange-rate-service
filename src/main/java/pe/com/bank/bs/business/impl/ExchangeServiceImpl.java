package pe.com.bank.bs.business.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.bank.bs.business.ExchangeService;
import pe.com.bank.bs.model.ExchangeRate;
import pe.com.bank.bs.model.ExchangeResponse;
import pe.com.bank.bs.repository.ExchangeRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ExchangeServiceImpl implements ExchangeService {

    ExchangeRepository exchangeRepository;

    public Flux<ExchangeResponse> getAllStudent() {
        return exchangeRepository.findAll().map(abc -> ExchangeResponse.builder()
                .amountExchangeRate(abc.getAmountExchangeRate())
                .build());
    }

    public Mono<ExchangeResponse> convert(Double amount, String origenCurrency,
                                          String destinationCurrency) {
        return exchangeRepository.getExchangeRateBySourceCurrencyAndTargetCurrency(origenCurrency,destinationCurrency)
            .map(abc -> ExchangeResponse.builder()
                    .originCurrency(origenCurrency)
                    .destinationCurrency(destinationCurrency)
                    .amountExchangeRate(abc.getAmountExchangeRate())
                    .amountEnvio(amount)
                    .amountRecive(amount * abc.getAmountExchangeRate())
                    .build());
    }

    public Mono<ExchangeRate> update(int id, ExchangeRate exchangeRate) {
        return exchangeRepository.findById(id)
                .map(Optional::of)
                .defaultIfEmpty(Optional.empty())
                .flatMap(optionalTutorial -> {
                    if (optionalTutorial.isPresent()) {
                        exchangeRate.setId(id);
                        return exchangeRepository.save(exchangeRate);
                    }
                    return Mono.empty();
                });
    }
}