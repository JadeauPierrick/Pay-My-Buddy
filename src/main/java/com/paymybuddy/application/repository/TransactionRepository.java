package com.paymybuddy.application.repository;

import com.paymybuddy.application.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

    public Page<Transaction> findByOriginalAccount(int id, Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT * FROM transaction WHERE account_id LIKE :id")
    public Page<Transaction> findByOriAccountId(@Param("id") int id, Pageable pageable);

    public List<Transaction> findByOriginalAccountId(int id);
}
