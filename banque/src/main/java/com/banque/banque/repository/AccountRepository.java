package com.banque.banque.repository;

import com.banque.banque.model.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {
    Account findByCardNumber(String cardNumber);

    @Query("SELECT SUM(amount) FROM Transaction t WHERE amount >= 0 AND t.account =:account ")
    Double getTotalTransactionsPositivesByAccount(@Param("account") Account account);

    @Query("SELECT SUM(amount) FROM Transaction t WHERE amount < 0 AND t.account =:account ")
    Double getTotalTransactionsNegativesByAccount(@Param("account") Account account);
}
