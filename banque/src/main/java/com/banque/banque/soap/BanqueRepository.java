package com.banque.banque.soap;

import org.springframework.stereotype.Component;

@Component
public class BanqueRepository {

    public boolean refundCustomer(String cardNumber) {
        return true;
    }

    public boolean debitCustomer(String cardNumber) {
        return true;
    }

}
