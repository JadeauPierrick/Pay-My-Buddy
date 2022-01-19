package com.paymybuddy.application.service;

import com.paymybuddy.application.model.BankTransaction;
import com.paymybuddy.application.repository.BankTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BankTransactionService {

    @Autowired
    private BankTransactionRepository bankTransactionRepository;

    public Iterable<BankTransaction> getBankTransactions(){
        return bankTransactionRepository.findAll();
    }

    public Optional<BankTransaction> getBankTransactionById(Integer id){
        return bankTransactionRepository.findById(id);
    }

    public BankTransaction addBankTransaction(BankTransaction bankTransaction){
        return bankTransactionRepository.save(bankTransaction);
    }

    public void deleteBankTransactionById(Integer id){
        bankTransactionRepository.deleteById(id);
    }
}
