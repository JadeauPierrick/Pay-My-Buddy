package com.paymybuddy.application.service;

import com.paymybuddy.application.model.Account;
import com.paymybuddy.application.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Iterable<Account> getAccounts(){
        return accountRepository.findAll();
    }

    @Override
    public Optional<Account> getAccountById(Integer id){
        return accountRepository.findById(id);
    }

    @Override
    public Account addAccount(Account account){
        return accountRepository.save(account);
    }

    @Override
    public void deleteAccountById(Integer id){
        accountRepository.deleteById(id);
    }
}
