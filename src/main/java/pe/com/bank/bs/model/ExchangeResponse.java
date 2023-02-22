package pe.com.bank.bs.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeResponse {

    private Double amountExchangeRate;
    private Double amountEnvio;
    private Double amountRecive;
    private String originCurrency;
    private String destinationCurrency;

}