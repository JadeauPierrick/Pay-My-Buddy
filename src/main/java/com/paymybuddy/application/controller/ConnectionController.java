package com.paymybuddy.application.controller;

import com.paymybuddy.application.model.Connection;
import com.paymybuddy.application.model.User;
import com.paymybuddy.application.service.ConnectionServiceImpl;
import com.paymybuddy.application.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
public class ConnectionController {

    @Autowired
    private ConnectionServiceImpl connectionService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/connection", method = RequestMethod.POST)
    public String addConnection(Authentication authentication, HttpServletRequest request, Model model) throws Exception {
        User user = userService.getUserByEmail(authentication.getName());

        try{
            User buddy = userService.getUserByEmail(request.getParameter("buddyMail"));
            Connection connection = new Connection();
            connection.setUser(user);
            connection.setBuddy(buddy);
            connectionService.addConnection(connection);
            user.addConnection(connection);
            String success = "Your buddy has been added";
            model.addAttribute("success", success);
            log.info("New connection between " + user.getFirstName() + " " + user.getLastName() + " and " + buddy.getFirstName() + " " + buddy.getLastName());
        }catch (Exception e){
            String error = "The user was not found";
            model.addAttribute("error", error);
        }

        return "redirect:/transfer";
    }
}
