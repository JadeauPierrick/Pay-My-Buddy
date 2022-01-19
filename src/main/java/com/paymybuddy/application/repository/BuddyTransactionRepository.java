package com.paymybuddy.application.repository;

import com.paymybuddy.application.model.BuddyTransaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuddyTransactionRepository extends CrudRepository<BuddyTransaction, Integer> {
}
