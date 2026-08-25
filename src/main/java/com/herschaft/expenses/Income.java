package com.herschaft.expenses;

public class Income extends Transaction {
    public Income(double value, String description) {
        super(value, description, TransactionType.INCOME);
    }
}
