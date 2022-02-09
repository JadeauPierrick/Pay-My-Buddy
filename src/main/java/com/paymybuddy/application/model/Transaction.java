package com.paymybuddy.application.model;

import com.paymybuddy.application.constants.TransactionType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@DynamicUpdate
public class Transaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date date;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @NotNull(message = "The amount is required")
    @Column(length = 20)
    private float amount;

    private float fees;

    @NotNull(message = "The description is required")
    private String description;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account originalAccount;

    @Column(name = "buddy_account_id")
    private Account buddyAccount;

}
