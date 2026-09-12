package com.herschaft.expenses.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Transaction {

    private final double amount;
    private final String description;
    private final TransactionType transactionType;
    private int id;

    @JsonCreator
    public Transaction(
        @JsonProperty("amount") double amount,
        @JsonProperty("description") String description,
        @JsonProperty("transactionType") TransactionType transactionType
    ) {
        this.amount = amount;
        this.description = description;
        this.transactionType = transactionType;
    }

    public Transaction(int id, double amount, String description, TransactionType transactionType){
        this.amount = amount;
        this.description = description;
        this.transactionType = transactionType;
        this.id = id;
    }

    public double getAmount(){
        return this.amount;
    }

    public String getDescription(){
        return this.description;
    }

    public TransactionType getTransactionType(){
        return this.transactionType;
    }
    
    public String getTransactionTypeString(){
        if(this.transactionType.equals(TransactionType.EXPENSE)){
            return "Gasto";
        } else {
            return "Ganho";
        }
    }

    public double getSignedAmount(){
        if(this.transactionType.equals(TransactionType.EXPENSE)){
            return -this.amount;
        } else {
            return this.amount;
        }
    }

    public int getId() {
        return this.id;
    }

}
