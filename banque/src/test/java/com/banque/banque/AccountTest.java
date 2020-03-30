package com.banque.banque;


import com.banque.banque.model.Account;
import com.banque.banque.repository.AccountRepository;
import com.banque.banque.service.AccountServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.is;

@RunWith(MockitoJUnitRunner.class)
public class AccountTest {

    @Test
    public void findAccount() {
        Account accountMocked = Account
                .builder()
                .balance(1000)
                .cardNumber("1111-2222-3333-4444")
                .transactions(new ArrayList<>())
                .build();
        AccountRepository mockitoAccountRepository = mock(AccountRepository.class);
        when(mockitoAccountRepository.findByCardNumber(any())).thenReturn(accountMocked);

        AccountServiceImpl obj = new AccountServiceImpl();
        obj.setAccountRepository(mockitoAccountRepository);

        //2. Test method
        Account result = obj.findByCardNumber("1111-2222-3333-4444");;

        //3. Verify result
        assertThat(result.getBalance(), is(1000.0));
    }
}
