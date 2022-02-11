package com.paymybuddy.application.repository;

import com.paymybuddy.application.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    public Page<Transaction> findByOriginalAccountId(int id, Pageable pageable);

}
