package fr.commerce.ecommerce.rest.model;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConnexionUserOdt implements Serializable {
    private String email;
    private String password;
}
