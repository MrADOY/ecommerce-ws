package com.banque.banque.controller;

import com.banque.banque.config.UserPrincipal;
import com.banque.banque.model.User;
import com.banque.banque.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public User userRegistrationDto() {
        return new User();
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUserAccount(@ModelAttribute("user")User userDto,
                                      BindingResult result){

        User existing = userService.findByEmail(userDto.getEmail());
        if (existing != null){
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        if (result.hasErrors()){
            return "registration";
        }

        userService.save(userDto);
        return "redirect:/login";
    }

    @GetMapping("/user")
    public String userIndex(Model model) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User utilisateur = userService.findById(((UserPrincipal)principal).getId());
        model.addAttribute("user", utilisateur);

        return "user";
    }

    @GetMapping("/user/edit")
    public String userEdit(Model model) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User utilisateur = userService.findById(((UserPrincipal)principal).getId());
        model.addAttribute("user", utilisateur);
        return "user_form";
    }

    @PostMapping("/user/update")
    public String updateUserAccount(@ModelAttribute("user") @Valid User userDto, BindingResult result){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = ((UserPrincipal)principal).getUsername();
        Boolean existing = false;
        User user = userService.findByEmail(userDto.getEmail());
        if (user.getId() != ((UserPrincipal)principal).getId()) {
            existing = true;
        }
        if (existing == true){
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        if (result.hasErrors()){
            return "user_form";
        }

        userService.update(email, userDto);
        return "redirect:/user";
    }
}
