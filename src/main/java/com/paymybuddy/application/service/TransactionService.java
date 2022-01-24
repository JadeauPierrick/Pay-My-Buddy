package com.paymybuddy.application.service;

import com.paymybuddy.application.model.Transaction;

import java.util.Optional;

public interface TransactionService {

    public Iterable<Transaction> getTransactions();

    public Optional<Transaction> getTransactionById(Integer id);

    public Transaction addTransaction(Transaction transaction);

    public void deleteTransactionById(Integer id);
}
