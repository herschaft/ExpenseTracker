package com.herschaft.expenses;

public class Transaction {

    private final double amount;
    private final String description;
    private final boolean negative;

    public Transaction(double amount, String description, Boolean negative){

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

    public Boolean getNegative(){
        return this.negative;
    }
    
    public String getNegativeString(){
        if(this.negative){
            return "Negativo";
        } else {
            return "Adição";
        }
    }

    public double getSignedAmount(){
        return negative ? -amount : amount;
    }

}
