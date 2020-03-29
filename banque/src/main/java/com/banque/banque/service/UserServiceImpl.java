package com.banque.banque.service;

import com.banque.banque.config.UserPrincipal;
import com.banque.banque.model.Role;
import com.banque.banque.model.User;
import com.banque.banque.model.Account;
import com.banque.banque.repository.AccountRepository;
import com.banque.banque.service.AccountService;
import com.banque.banque.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(usernameOrEmail);
        return UserPrincipal.create(user);
    }

    @Override
    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User save(User registration){
        Account account = new Account();
        account.setBalance(1000);
        int one = (int)(Math.random() * (9999 - 1000 + 1) + 1000);
        int two = (int)(Math.random() * (9999 - 1000 + 1) + 1000);
        int three = (int)(Math.random() * (9999 - 1000 + 1) + 1000);
        int four = (int)(Math.random() * (9999 - 1000 + 1) + 1000);
        String cardNumber = "" + one + "-" + two + "-" + three + "-" + four;

        Account existing = accountService.findByCardNumber(cardNumber);
        while(existing != null) {
            one = (int)(Math.random() * (9999 - 1000 + 1) + 1000);
            two = (int)(Math.random() * (9999 - 1000 + 1) + 1000);
            three = (int)(Math.random() * (9999 - 1000 + 1) + 1000);
            four = (int)(Math.random() * (9999 - 1000 + 1) + 1000);
            cardNumber = "" + one + "-" + two + "-" + three + "-" + four;
        }

        account.setCardNumber(cardNumber);
        account = accountRepository.save(account);

        User user = new User();
        user.setEmail(registration.getEmail());
        user.setPassword(passwordEncoder.encode(registration.getPassword()));
        user.setRoles(Arrays.asList(new Role("ROLE_USER")));
        user.setAccount(account);
        user = userRepository.save(user);
        account.setUser(user);
        return user;
    }

    @Override
    public User update(String email, User userUpdated){
        User user = userRepository.findByEmail(email);
        user.setEmail(userUpdated.getEmail());
        return userRepository.save(user);
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
