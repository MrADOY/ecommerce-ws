package fr.commerce.ecommerce.rest.model;

import java.io.Serializable;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserOdt implements Serializable {
    private String email;
    private String password;
    private String bankCardNumber;
    private String name;
    private String firstname;
}
