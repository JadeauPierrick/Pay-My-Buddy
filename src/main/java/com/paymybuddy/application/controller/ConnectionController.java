package com.paymybuddy.application.controller;

import com.paymybuddy.application.model.Connection;
import com.paymybuddy.application.model.User;
import com.paymybuddy.application.service.ConnectionServiceImpl;
import com.paymybuddy.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ConnectionController {

    @Autowired
    private ConnectionServiceImpl connectionService;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/connection")
    public String addConnection(Authentication authentication, @ModelAttribute("newConnection") String email, Model model) throws Exception {
        String userEmail = authentication.getName();
        User user = userService.getUserByEmail(userEmail);

        User buddy = userService.getUserByEmail(email);

        Connection connection = new Connection();
        connection.setUser(user);
        connection.setBuddy(buddy);
        connectionService.addConnection(connection);
        user.addConnection(connection);

        return "redirect/transfer";
    }
}
