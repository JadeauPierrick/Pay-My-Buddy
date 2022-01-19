package com.paymybuddy.application.repository;

import com.paymybuddy.application.model.BankTransaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankTransactionRepository extends CrudRepository<BankTransaction, Integer> {
}
