package com.paymybuddy.application.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @GetMapping("/login")
    public String getUser(){
        return "Hello";
    }

    @GetMapping("/home")
    public String homePage(){
        return "Welcome";
    }
}
