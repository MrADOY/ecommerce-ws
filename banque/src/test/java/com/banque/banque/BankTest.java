package com.banque.banque;


import com.banque.banque.model.Account;
import com.banque.banque.model.Transaction;
import com.banque.banque.repository.AccountRepository;
import com.banque.banque.repository.TransactionsRepository;
import com.banque.banque.service.BankServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BankTest {

    @Test
    public void refundCustomer() {

        Account accountMocked = Account
                .builder()
                .balance(1000)
                .cardNumber("1111-2222-3333-4444")
                .transactions(new ArrayList<>())
                .build();

        Transaction transaction = Transaction
                .builder()
                .account(accountMocked)
                .amount(20d)
                .build();

        AccountRepository mockitoAccountRepository = mock(AccountRepository.class);
        TransactionsRepository mockitoTransactionRepository = mock(TransactionsRepository.class);

        when(mockitoAccountRepository.findByCardNumber(any())).thenReturn(accountMocked);
        when(mockitoTransactionRepository.save(any())).thenReturn(transaction);
        when(mockitoAccountRepository.save(any())).thenReturn(accountMocked);

        BankServiceImpl obj = new BankServiceImpl();
        obj.setAccountRepository(mockitoAccountRepository);
        obj.setTransactionsRepository(mockitoTransactionRepository);

        //2. Test method
        boolean result = obj.refundCustomer("1111-2222-3333-4444", 50d);;

        //3. Verify result
        assertTrue(result);
    }

    @Test
    public void debitCustomer() {

        Account accountMocked = Account
                .builder()
                .balance(1000)
                .cardNumber("1111-2222-3333-4444")
                .transactions(new ArrayList<>())
                .build();

        Transaction transaction = Transaction
                .builder()
                .account(accountMocked)
                .amount(20d)
                .build();

        AccountRepository mockitoAccountRepository = mock(AccountRepository.class);
        TransactionsRepository mockitoTransactionRepository = mock(TransactionsRepository.class);

        when(mockitoAccountRepository.findByCardNumber(any())).thenReturn(accountMocked);
        when(mockitoTransactionRepository.save(any())).thenReturn(transaction);
        when(mockitoAccountRepository.save(any())).thenReturn(accountMocked);

        BankServiceImpl obj = new BankServiceImpl();
        obj.setAccountRepository(mockitoAccountRepository);
        obj.setTransactionsRepository(mockitoTransactionRepository);

        //2. Test method
        boolean result = obj.debitCustomer("1111-2222-3333-4444", 50d);;

        //3. Verify result
        assertTrue(result);
    }
}
