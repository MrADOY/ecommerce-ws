package com.banque.banque.repository;

import com.banque.banque.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {
    Account findByCardNumber(String cardNumber);
}
