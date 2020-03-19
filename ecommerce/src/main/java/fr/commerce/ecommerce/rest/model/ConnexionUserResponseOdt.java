package fr.commerce.ecommerce.rest.model;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConnexionUserResponseOdt implements Serializable {
    private int id;
    private String email;
    private String name;
    private String firstname;
}
