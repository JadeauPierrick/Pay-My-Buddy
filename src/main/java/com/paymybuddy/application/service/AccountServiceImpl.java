package com.paymybuddy.application.service;

import com.paymybuddy.application.constants.TransactionType;
import com.paymybuddy.application.model.Account;
import com.paymybuddy.application.model.Transaction;
import com.paymybuddy.application.repository.AccountRepository;
import com.paymybuddy.application.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<Account> getAccounts(){
        return accountRepository.findAll();
    }

    @Override
    public Optional<Account> getAccountById(Integer id){
        return accountRepository.findById(id);
    }

    @Override
    public void makeATransfer(Account originalAccount, Account buddyAccount, TransactionType transactionType, BigDecimal amount, String description) throws Exception{
        BigDecimal fees = amount.multiply(new BigDecimal("0.005"));
        BigDecimal transfer = amount.add(fees);

        if (originalAccount.getBalance().compareTo(transfer) < 0){
            throw new Exception("Your balance is not enough");
        }
        originalAccount.setBalance(originalAccount.getBalance().subtract(transfer));
        buddyAccount.setBalance(buddyAccount.getBalance().add(amount));
        accountRepository.save(originalAccount);
        accountRepository.save(buddyAccount);

        Transaction transaction = new Transaction();
        Timestamp date = Timestamp.from(Instant.now());
        transaction.setDate(date);
        transaction.setTransactionType(transactionType);
        transaction.setAmount(amount);
        transaction.setFees(fees);
        transaction.setDescription(description);
        transaction.setOriginalAccount(originalAccount);
        transaction.setBuddyAccount(buddyAccount);
        transactionRepository.save(transaction);
    }

    @Override
    public void makeABankTransfer(Account account, TransactionType transactionType, BigDecimal amount, String description) throws Exception{
        BigDecimal fees = amount.multiply(new BigDecimal("0.005"));

        if (transactionType.equals(TransactionType.BANK_DEPOSIT)){
            BigDecimal deposit = amount.subtract(fees);
            account.setBalance(account.getBalance().add(deposit));
        }else{
            BigDecimal withdrawal = amount.add(fees);
            if (account.getBalance().compareTo(withdrawal) < 0){
                throw new Exception("Your balance is not enough");
            }
            account.setBalance(account.getBalance().subtract(withdrawal));
        }

        accountRepository.save(account);

        Transaction transaction = new Transaction();
        Timestamp date = Timestamp.from(Instant.now());
        transaction.setDate(date);
        transaction.setTransactionType(transactionType);
        transaction.setAmount(amount);
        transaction.setFees(fees);
        transaction.setDescription(description);
        transaction.setOriginalAccount(account);
        transaction.setBuddyAccount(account);
        transactionRepository.save(transaction);
    }

    @Override
    public void deleteAccountById(Integer id){
        accountRepository.deleteById(id);
    }
}
