package com.banque.banque.service;

import com.banque.banque.model.Account;

public interface AccountService {
    Account findByCardNumber(String number);

    Double getTotalTransactionsPositivesByAccount(Account account);

    Double getTotalTransactionsNegativesByAccount(Account account);
}
