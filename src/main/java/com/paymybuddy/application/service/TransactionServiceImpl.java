package com.paymybuddy.application.service;

import com.paymybuddy.application.model.Transaction;
import com.paymybuddy.application.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public Iterable<Transaction> getTransactions(){
        return transactionRepository.findAll();
    }

    @Override
    public Optional<Transaction> getTransactionById(Integer id){
        return transactionRepository.findById(id);
    }

    @Override
    public Transaction addTransaction(Transaction transaction){
        return transactionRepository.save(transaction);
    }

    @Override
    public void deleteTransactionById(Integer id){
        transactionRepository.deleteById(id);
    }
}
