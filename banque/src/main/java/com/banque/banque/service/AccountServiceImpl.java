package com.banque.banque.service;

import com.banque.banque.model.Account;
import com.banque.banque.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account findByCardNumber(String number){
        return accountRepository.findByCardNumber(number);
    }
}
