package com.herschaft.expenses;

public class Transaction {

    private final double amount;
    private final String description;
    private final TransactionType negative;

    public Transaction(double amount, String description, TransactionType negative){
        this.amount = amount;
        this.description = description;
        this.negative = negative;
    }

    public double getAmount(){
        return this.amount;
    }

    public String getDescription(){
        return this.description;
    }

    public TransactionType getTransactionType(){
        return this.negative;
    }
    
    public String getTransactioTypeString(){
        if(this.negative.equals(TransactionType.EXPENSE)){
            return "Gasto";
        } else {
            return "Ganho";
        }
    }

    public double getSignedAmount(){
        if(this.negative.equals(TransactionType.EXPENSE)){
            return -this.amount;
        } else {
            return this.amount;
        }
    }

}
