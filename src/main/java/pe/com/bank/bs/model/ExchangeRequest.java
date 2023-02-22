package pe.com.bank.bs.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExchangeRequest {

    private Double amount;
    private String origenCurrency;
    private String destinationCurrency;
}
