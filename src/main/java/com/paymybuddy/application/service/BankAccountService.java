package com.paymybuddy.application.service;

import com.paymybuddy.application.model.BankAccount;
import com.paymybuddy.application.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    public Iterable<BankAccount> getBankAccounts(){
        return bankAccountRepository.findAll();
    }

    public Optional<BankAccount> getBankAccountById(Integer id){
        return bankAccountRepository.findById(id);
    }

    public BankAccount addBankAccount(BankAccount bankAccount){
        return bankAccountRepository.save(bankAccount);
    }

    public void deleteBankAccountById(Integer id){
        bankAccountRepository.deleteById(id);
    }
}
