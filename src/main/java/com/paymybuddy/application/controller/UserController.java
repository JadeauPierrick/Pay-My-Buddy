package com.paymybuddy.application.controller;

import com.paymybuddy.application.model.User;
import com.paymybuddy.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/home")
    public String homePage(Authentication authentication, Model model) throws Exception {
        User user = userService.getUserByEmail(authentication.getName());

        model.addAttribute("balance", user.getAccount().getBalance());
        model.addAttribute("hello", "Hello " + user.getFirstName());
        return "home";
    }
}
