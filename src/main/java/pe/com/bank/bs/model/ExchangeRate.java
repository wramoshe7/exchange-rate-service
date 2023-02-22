package pe.com.bank.bs.model;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRate {

    @Id
    private int id;
    private String sourceCurrency;

    private String targetCurrency;

    private Double amountExchangeRate;

}