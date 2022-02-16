package com.paymybuddy.application.service;

import com.paymybuddy.application.model.Account;
import com.paymybuddy.application.model.Transaction;
import com.paymybuddy.application.model.User;
import com.paymybuddy.application.repository.AccountRepository;
import com.paymybuddy.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Account> getAccounts(){
        return accountRepository.findAll();
    }

    @Override
    public Optional<Account> getAccountById(Integer id){
        return accountRepository.findById(id);
    }

    @Override
    public void makeATransfer(Account originalAccount, Account buddyAccount, BigDecimal amount, String description) throws Exception{

    }


    @Override
    public void deleteAccountById(Integer id){
        accountRepository.deleteById(id);
    }
}
