package pe.com.bank.bs.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.com.bank.bs.business.ExchangeService;
import pe.com.bank.bs.model.ExchangeRate;
import pe.com.bank.bs.model.ExchangeResponse;
import pe.com.bank.bs.model.UserExchange;
import pe.com.bank.bs.repository.ExchangeRepository;
import pe.com.bank.bs.repository.UserRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/v1")
public class ExchangeController {

    private ExchangeService exchangeService;
    private ExchangeRepository exchangeRepository;
    private UserRepository userRepository;


    @GetMapping("/exchange")
    @ResponseStatus(HttpStatus.OK)
    public Flux<ExchangeRate> convert() {
        return exchangeRepository.findAll();
    }

    @PostMapping("/exchange")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ExchangeRate> convert(@RequestBody ExchangeRate exchangeRate) {
        return exchangeRepository.save(exchangeRate);
    }

    @PutMapping("/exchange/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<ExchangeRate> convert(@PathVariable("id") int id,
                                      @RequestBody ExchangeRate exchangeRate) {
        return exchangeService.update(id,exchangeRate);
    }

    @GetMapping("/exchange/rate")
    @ResponseStatus(HttpStatus.OK)
    public Mono<ExchangeResponse> convertType(@RequestParam("amount") Double amount,
                                              @RequestParam("origenCurrency") String origenCurrency,
                                              @RequestParam("destinationCurrency") String destinationCurrency) {
        return exchangeService.convert(amount,origenCurrency,destinationCurrency);
    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<UserExchange> convert(@RequestBody UserExchange user) {
        return userRepository.save(user);
    }

    @GetMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public Flux<UserExchange> convertd() {
        return userRepository.findAll();
    }

}