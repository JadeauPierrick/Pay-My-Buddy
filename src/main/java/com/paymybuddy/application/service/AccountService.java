package com.paymybuddy.application.service;

import com.paymybuddy.application.model.Account;

import java.util.Optional;

public interface AccountService {

    public Iterable<Account> getAccounts();

    public Optional<Account> getAccountById(Integer id);

    public void deleteAccountById(Integer id);
}
