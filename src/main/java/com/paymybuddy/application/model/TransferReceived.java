package com.paymybuddy.application.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("TransferReceived")
public class TransferReceived extends Transaction{
}
