package com.paymybuddy.application.repository;

import com.paymybuddy.application.model.Connection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnectionRepository extends JpaRepository<Connection, Integer> {
}
