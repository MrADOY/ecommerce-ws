package com.banque.banque.repository;

import com.banque.banque.model.Transaction;
import com.banque.banque.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionsRepository extends CrudRepository<Transaction, Integer> {

}
