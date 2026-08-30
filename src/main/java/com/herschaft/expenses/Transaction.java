package com.herschaft.expenses;

public class Transaction {

    private final double amount;
    private final String description;
    private final TransactionType transactionType;

    public Transaction(double amount, String description, TransactionType transactionType){
        this.amount = amount;
        this.description = description;
        this.transactionType = transactionType;
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

}
