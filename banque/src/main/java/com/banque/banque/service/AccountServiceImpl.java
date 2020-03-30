package com.banque.banque.service;

import com.banque.banque.model.Account;
import com.banque.banque.model.Transaction;
import com.banque.banque.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account findByCardNumber(String number){
        return accountRepository.findByCardNumber(number);
    }
    
    public AccountRepository getAccountRepository() {
        return accountRepository;
    }

    public void setAccountRepository(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public double getTotalTransactionsPositivesByAccount(Account account){
        return accountRepository.getTotalTransactionsPositivesByAccount(account);
    }

    @Override
    public double getTotalTransactionsNegativesByAccount(Account account){
        return accountRepository.getTotalTransactionsNegativesByAccount(account);
    }
}
