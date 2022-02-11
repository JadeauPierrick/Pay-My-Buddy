package com.paymybuddy.application.service;

import com.paymybuddy.application.model.Account;
import com.paymybuddy.application.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Account> getAccounts(){
        return accountRepository.findAll();
    }

    @Override
    public Optional<Account> getAccountById(Integer id){
        return accountRepository.findById(id);
    }

    @Override
    public void deleteAccountById(Integer id){
        accountRepository.deleteById(id);
    }
}
