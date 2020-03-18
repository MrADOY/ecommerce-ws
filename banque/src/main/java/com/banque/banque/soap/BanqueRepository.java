package com.banque.banque.soap;

import com.banque.banque.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BanqueRepository {

    @Autowired
    BankService bankService;

    public boolean refundCustomer(String cardNumber, double amount) {
        return bankService.refundCustomer(cardNumber, amount);
    }

    public boolean debitCustomer(String cardNumber, double amount) {
        return bankService.debitCustomer(cardNumber, amount);
    }
}
