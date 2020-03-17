package com.banque.banque.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String root(Model model) {
        model.addAttribute("model", "Aurélien");
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

}
