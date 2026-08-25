package com.herschaft.expenses;

public class Expense extends Transaction {
    public Expense(double value, String description) {
        super(value, description, TransactionType.EXPENSE);
    }
}
