package com.paymybuddy.application.controller;

import com.paymybuddy.application.model.User;
import com.paymybuddy.application.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/home")
    public String homePage(Authentication authentication, Model model) throws Exception {
        User user = userService.getUserByEmail(authentication.getName());

        model.addAttribute("balance", user.getAccount().getBalance());
        model.addAttribute("hello", "Hello " + user.getFirstName());
        log.info("Get homepage of " + user.getFirstName() + " " + user.getLastName());
        return "home";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String addUser(@ModelAttribute User user, Model model){
        try {
            userService.addUser(user);
            String message = "Your information has been successfully saved";
            model.addAttribute("message", message);
            log.info("New user : " + user.getEmail());
        }catch (Exception e){
            String emailError = "This email is already used";
            model.addAttribute("emailError", emailError);
        }

        return "login";
    }
}
