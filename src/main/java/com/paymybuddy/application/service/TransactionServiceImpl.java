package com.paymybuddy.application.service;

import com.paymybuddy.application.model.Transaction;
import com.paymybuddy.application.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<Transaction> getTransactions(){
        return transactionRepository.findAll();
    }

    @Override
    public Optional<Transaction> getTransactionById(Integer id){
        return transactionRepository.findById(id);
    }

    @Override
    public Page<Transaction> getTransactionsByAccountOriginalId(Integer id, PageRequest pageRequest){
        return transactionRepository.findByOriginalAccountId(id, pageRequest);
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
