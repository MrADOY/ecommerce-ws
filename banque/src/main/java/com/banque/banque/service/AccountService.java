package com.banque.banque.service;

import com.banque.banque.model.Account;

public interface AccountService {
    Account findByCardNumber(String number);

    double getTotalTransactionsPositivesByAccount(Account account);

    double getTotalTransactionsNegativesByAccount(Account account);
}
