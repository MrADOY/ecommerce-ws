package fr.commerce.ecommerce.rest.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@Data

@NoArgsConstructor
@AllArgsConstructor
public class CreateProductOdt implements Serializable {
    private String name;
    private String description;
    private String urlPictures;
    private double price;
    private int ownerId;
}
