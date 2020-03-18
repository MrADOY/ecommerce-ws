package com.banque.banque.service;



public interface BankService {
    boolean refundCustomer(String cardNumber, double amount);
    boolean debitCustomer(String cardNumber, double amount);
}
