package com.paymybuddy.application.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("BankDeposit")
public class BankDeposit extends Transaction{
}
