package com.paymybuddy.application.service;

import com.paymybuddy.application.model.Transaction;
import com.paymybuddy.application.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public List<Transaction> getTransactionsByOriginalAccountId(int id){
        return transactionRepository.findByOriginalAccountId(id);
    }

    @Override
    public Page<Transaction> getTransactionsByOriginalAccount(Integer id, PageRequest pageRequest){
        return transactionRepository.findByOriginalAccount(id, pageRequest);
    }

    @Override
    public Page<Transaction> getTransactionsByOriAccountId(Integer id, PageRequest pageRequest){
        return transactionRepository.findByOriAccountId(id, pageRequest);
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
