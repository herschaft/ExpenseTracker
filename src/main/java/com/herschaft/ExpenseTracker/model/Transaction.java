package com.herschaft.ExpenseTracker.model;

import java.math.BigDecimal;

public record Transaction(BigDecimal amount, String description, TransactionType type, Long id)  {

    public Transaction(BigDecimal amount, String description, TransactionType type) {
        this(amount, description, type, null);  
    }

    public Transaction income(BigDecimal amount, String description) {
        return new Transaction(amount, description, TransactionType.INCOME);
    }
    
    public Transaction expense(BigDecimal amount, String description) {
        return new Transaction(amount, description, TransactionType.EXPENSE);
    }

    public BigDecimal getSignedAmount(){
        if(this.type.equals(TransactionType.EXPENSE)){
            return this.amount.negate();
        }
        
        return this.amount;
    }

}
