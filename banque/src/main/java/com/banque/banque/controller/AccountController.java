package com.banque.banque.controller;

import com.banque.banque.config.UserPrincipal;
import com.banque.banque.model.Account;
import com.banque.banque.model.User;
import com.banque.banque.service.AccountService;
import com.banque.banque.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @GetMapping("/account")
    public String root(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findById(((UserPrincipal)principal).getId());
        Account account = user.getAccount();
        model.addAttribute("user", user);
        model.addAttribute("account", account);
        model.addAttribute("transactions", account.getTransactions());
        model.addAttribute("debits", accountService.getTotalTransactionsNegativesByAccount(account));
        model.addAttribute("credits", accountService.getTotalTransactionsPositivesByAccount(account));
        return "account";
    }

}
