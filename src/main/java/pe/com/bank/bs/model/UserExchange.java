package pe.com.bank.bs.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserExchange {

    @Id
    private int id;
    private String name;
    private String username;
    private String password;
    private String rol;

}
