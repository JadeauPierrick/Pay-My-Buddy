package com.paymybuddy.application.controller;

import com.paymybuddy.application.constants.TransactionType;
import com.paymybuddy.application.model.Connection;
import com.paymybuddy.application.model.Transaction;
import com.paymybuddy.application.model.User;
import com.paymybuddy.application.service.AccountService;
import com.paymybuddy.application.service.TransactionService;
import com.paymybuddy.application.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Controller
public class TransactionController {

    private UserService userService;

    private TransactionService transactionService;

    private AccountService accountService;

    public TransactionController(UserService userService, TransactionService transactionService, AccountService accountService) {
        this.userService = userService;
        this.transactionService = transactionService;
        this.accountService = accountService;
    }

    @GetMapping(value = "/transfer")
    public String transferPage(Authentication authentication, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name="size", defaultValue = "4") int size, Model model) throws Exception {
        User user = userService.getUserByEmail(authentication.getName());
        List<User> buddies = user.getConnections().stream().map(Connection::getBuddy).collect(Collectors.toList());

        Page<Transaction> transactions = transactionService.getTransactionsByAccountOriginalId(user.getAccount().getId(), PageRequest.of(page, size, Sort.by("date").descending()));
        List<Integer> totalPages = IntStream.rangeClosed(0, transactions.getTotalPages() - 1).boxed().collect(Collectors.toList());

        model.addAttribute("totalPages", totalPages);
        model.addAttribute("transactions", transactions);
        model.addAttribute("currentPage", page);
        model.addAttribute("buddies", buddies);
        log.info("Get transfer page of " + user.getFirstName() + " " + user.getLastName());
        return "transfer";
    }

    @RequestMapping(value = "/transfer/new", method = RequestMethod.POST)
    public String newTransfer(@ModelAttribute("newTransaction") Transaction transaction, BindingResult result, HttpServletRequest request, Authentication authentication, Model model) throws Exception {

        User user = userService.getUserByEmail(authentication.getName());
        String errorTransfer = "Your balance is not enough";

        if (transaction.getTransactionType().equals(TransactionType.TRANSFER)){
            try {
                User buddy = userService.getUserByEmail(request.getParameter("buddyAccount"));
                accountService.makeATransfer(user.getAccount(), buddy.getAccount(), transaction.getTransactionType(), transaction.getAmount(), transaction.getDescription());
                log.info("New transfer between " + user.getFirstName() + " " + user.getLastName() + " and " + buddy.getFirstName() + " " + buddy.getLastName());
            }catch (Exception e){
                model.addAttribute("errorTransfer", errorTransfer);
            }
        }else {
            try {
                accountService.makeABankTransfer(user.getAccount(), transaction.getTransactionType(), transaction.getAmount(), transaction.getDescription());
                log.info("New bank transfer " + transaction.getTransactionType() + " by " + user.getFirstName() + " " + user.getLastName());
            }catch (Exception e){
                model.addAttribute("errorTransfer", errorTransfer);
            }
        }



        return "redirect:/transfer";
    }
}
