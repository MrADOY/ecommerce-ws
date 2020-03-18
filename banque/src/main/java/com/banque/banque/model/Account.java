package com.banque.banque.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double balance;
    private String cardNumber;

    @OneToOne
    private User user;

    @OneToMany(mappedBy="account")
    private List<Transaction> transactions;

}
