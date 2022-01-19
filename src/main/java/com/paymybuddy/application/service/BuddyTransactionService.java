package com.paymybuddy.application.service;

import com.paymybuddy.application.model.BuddyTransaction;
import com.paymybuddy.application.repository.BuddyTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuddyTransactionService {

    @Autowired
    private BuddyTransactionRepository buddyTransactionRepository;

    public Iterable<BuddyTransaction> getBuddyTransactions(){
        return buddyTransactionRepository.findAll();
    }

    public Optional<BuddyTransaction> getBuddyTransactionById(Integer id){
        return buddyTransactionRepository.findById(id);
    }

    public BuddyTransaction addBuddyTransaction(BuddyTransaction buddyTransaction){
        return buddyTransactionRepository.save(buddyTransaction);
    }

    public void deleteBuddyTransactionById(Integer id){
        buddyTransactionRepository.deleteById(id);
    }
}
