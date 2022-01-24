package com.paymybuddy.application.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("BankWithdrawal")
public class BankWithdrawal extends Transaction{

}
