package com.paymybuddy.application.model;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@DynamicUpdate
@Table(name = "buddy_transaction")
public class BuddyTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "amount_before_fees")
    private float amountBeforeFees;

    private float fees;

    @Column(name = "final_amount")
    private float finalAmount;

    private String description;

    private Date date;
}
