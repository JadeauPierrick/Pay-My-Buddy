package com.paymybuddy.application.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private float balance;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id")
    private List<Transaction> transactions;

    public Account() {
    }
}
