package com.paymybuddy.application.service;

import com.paymybuddy.application.model.Account;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface AccountService {

    public List<Account> getAccounts();

    public Optional<Account> getAccountById(Integer id);

    public void makeATransfer(Account originalAccount, Account buddyAccount, BigDecimal amount, String description) throws Exception;

    public void deleteAccountById(Integer id);
}
