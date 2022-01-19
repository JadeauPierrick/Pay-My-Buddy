package com.paymybuddy.application.model;

import com.paymybuddy.application.constants.TransactionType;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@DynamicUpdate
@Table(name = "bank_transaction")
public class BankTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "type")
    private TransactionType transactionType;

    @Column(name = "amount_before_fees")
    private float amountBeforeFees;

    private float fees;

    @Column(name = "final_amount")
    private float finalAmount;

    private Date date;
}
