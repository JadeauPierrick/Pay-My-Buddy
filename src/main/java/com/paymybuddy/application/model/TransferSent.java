package com.paymybuddy.application.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("TransferSent")
public class TransferSent extends Transaction{
}
