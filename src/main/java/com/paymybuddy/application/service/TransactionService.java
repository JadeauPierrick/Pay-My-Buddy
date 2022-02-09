package com.paymybuddy.application.service;

import com.paymybuddy.application.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public interface TransactionService {

    public Iterable<Transaction> getTransactions();

    public Optional<Transaction> getTransactionById(Integer id);

    public Page<Transaction> getTransactionsByOriginalAccountId(Integer id, PageRequest pageRequest);

    public Page<Transaction> getTransactionsByOriAccountId(Integer id, PageRequest pageRequest);

    public Transaction addTransaction(Transaction transaction);

    public void deleteTransactionById(Integer id);
}
