package fr.commerce.ecommerce.rest;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserInputSoap implements Serializable {
    private int id;
    private double amount;
}
