package com.banque.banque.service;

import com.banque.banque.model.Account;
import com.banque.banque.model.Transaction;
import com.banque.banque.repository.AccountRepository;
import com.banque.banque.repository.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankServiceImpl implements BankService{

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransactionsRepository transactionsRepository;

    public boolean refundCustomer(String cardNumber, double amount){
        Account accountFound  = this.accountRepository.findByCardNumber(cardNumber);
        if(accountFound == null){
            return false;
        }
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setAccount(accountFound);
        transactionsRepository.save(transaction);

        accountFound.getTransactions().add(transaction);
        accountFound.setBalance(accountFound.getBalance() + amount);
        accountRepository.save(accountFound);
        return true;
    }

    public boolean debitCustomer(String cardNumber, double amount){
        Account accountFound  = this.accountRepository.findByCardNumber(cardNumber);
        if(accountFound == null){
            return false;
        }
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setAccount(accountFound);
        transactionsRepository.save(transaction);

        accountFound.getTransactions().add(transaction);
        accountFound.setBalance(accountFound.getBalance() + amount);
        accountRepository.save(accountFound);
        return true;
    }
}
