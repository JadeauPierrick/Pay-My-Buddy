package com.paymybuddy.application.service;

import com.paymybuddy.application.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    public List<Account> getAccounts();

    public Optional<Account> getAccountById(Integer id);

    public void deleteAccountById(Integer id);
}
