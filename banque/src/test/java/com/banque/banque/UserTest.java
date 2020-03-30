package com.banque.banque;


import com.banque.banque.model.Account;
import com.banque.banque.model.User;
import com.banque.banque.repository.AccountRepository;
import com.banque.banque.repository.UserRepository;
import com.banque.banque.service.AccountService;
import com.banque.banque.service.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

@RunWith(MockitoJUnitRunner.class)
public class UserTest {

    @Test
    public void findUserByEmailOk(){

        User userMocked = User.builder()
                .email("1@test.com")
                .firstName("firstname")
                .lastName("lastname")
                .build();

        UserRepository mockitoUserRepository = mock(UserRepository.class);

        when(mockitoUserRepository.findByEmail(any())).thenReturn(userMocked);

        UserServiceImpl obj = new UserServiceImpl();
        obj.setUserRepository(mockitoUserRepository);

        //2. Test method
        User userFound = obj.findByEmail("1@test.com");

        //3. Verify result
        assertThat(userFound.getEmail(), is("1@test.com"));
        assertThat(userFound.getFirstName(), is("firstname"));

    }

    @Test
    public void findUserByIdOk(){

        Optional<User> userMocked = Optional.of(User.builder()
                .email("1@test.com")
                .firstName("firstname")
                .lastName("lastname")
                .build());

        UserRepository mockitoUserRepository = mock(UserRepository.class);

        when(mockitoUserRepository.findById(any())).thenReturn(userMocked);

        UserServiceImpl obj = new UserServiceImpl();
        obj.setUserRepository(mockitoUserRepository);

        //2. Test method
        User userFound = obj.findById(1);

        //3. Verify result
        assertThat(userFound.getEmail(), is("1@test.com"));
        assertThat(userFound.getFirstName(), is("firstname"));

    }

    @Test
    public void testCreateUserOk(){

        Account accountMocked = Account
                .builder()
                .balance(1000)
                .cardNumber("1111-2222-3333-4444")
                .build();

        User userMocked = User.builder()
                .email("1@test.com")
                .firstName("firstname")
                .lastName("lastname")
                .account(accountMocked).build();

        AccountService mockitoService = mock(AccountService.class);
        AccountRepository mockitoRepo = mock(AccountRepository.class);
        BCryptPasswordEncoder mockitoPassword = mock(BCryptPasswordEncoder.class);
        UserRepository mockitoUserRepository = mock(UserRepository.class);

        when(mockitoService.findByCardNumber(any())).thenReturn(null);
        when(mockitoRepo.save(any())).thenReturn(accountMocked);
        when(mockitoPassword.encode(any())).thenReturn("PASSWORD ENCODED");
        when(mockitoUserRepository.save(any())).thenReturn(userMocked);

        UserServiceImpl obj = new UserServiceImpl();
        obj.setAccountService(mockitoService);
        obj.setAccountRepository(mockitoRepo);
        obj.setPasswordEncoder(mockitoPassword);
        obj.setUserRepository(mockitoUserRepository);

        User user = User.builder()
                .email("1@test.com")
                .firstName("firstname")
                .lastName("lastname")
                .build();
        //2. Test method
        User userSaved = obj.save(user);

        //3. Verify result
        assertThat(userSaved.getEmail(), is("1@test.com"));
        assertThat(userSaved.getFirstName(), is("firstname"));
        assertEquals(accountMocked, userSaved.getAccount());
    }
}
