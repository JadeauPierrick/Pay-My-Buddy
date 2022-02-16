package com.paymybuddy.application.controller;

import com.paymybuddy.application.model.Connection;
import com.paymybuddy.application.model.Transaction;
import com.paymybuddy.application.model.User;
import com.paymybuddy.application.service.TransactionService;
import com.paymybuddy.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TransactionController {

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionService transactionService;

    @GetMapping(value = "/transfer")
    public String transferPage(Authentication authentication, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name="size", defaultValue = "4") int size, Model model) throws Exception {
        User user = userService.getUserByEmail(authentication.getName());
        List<User> buddies = user.getConnections().stream().map(Connection::getBuddy).collect(Collectors.toList());

        Page<Transaction> transactions = transactionService.getTransactionsByAccountOriginalId(user.getAccount().getId(), PageRequest.of(page, size, Sort.by("date").descending()));

        model.addAttribute("transactions", transactions);
        model.addAttribute("currentPage", page);
        model.addAttribute("buddies", buddies);
        return "transfer";
    }

    @PostMapping(value = "/transfer/new")
    public String newTransfer(Authentication authentication, @ModelAttribute("newTransaction") String transactionType, Model model) throws Exception {
        User user = userService.getUserByEmail(authentication.getName());
        return "redirect:/transfer";
    }
}
