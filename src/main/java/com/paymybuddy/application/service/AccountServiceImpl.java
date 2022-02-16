package com.paymybuddy.application.service;

import com.paymybuddy.application.model.Account;
import com.paymybuddy.application.model.Transaction;
import com.paymybuddy.application.model.User;
import com.paymybuddy.application.repository.AccountRepository;
import com.paymybuddy.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Account> getAccounts(){
        return accountRepository.findAll();
    }

    @Override
    public Optional<Account> getAccountById(Integer id){
        return accountRepository.findById(id);
    }

    @Override
    public void makeATransfer(Account originalAccount, Account buddyAccount, float amount, String description) throws Exception{
        if (originalAccount.getBalance() < amount){
            throw new Exception("Your balance is not enough");
        }
        float newBalance = originalAccount.getBalance() - amount;
        float newBuddyBalance = buddyAccount.getBalance() + amount;
        originalAccount.setBalance(newBalance);
        accountRepository.save(originalAccount);
        buddyAccount.setBalance(newBuddyBalance);
        accountRepository.save(buddyAccount);

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setFees(amount);
    }


    @Override
    public void deleteAccountById(Integer id){
        accountRepository.deleteById(id);
    }
}
