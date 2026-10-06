package com.herschaft.ExpenseTracker.validation;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.herschaft.ExpenseTracker.exception.InvalidTransactionException;
import com.herschaft.ExpenseTracker.model.Transaction;
import com.herschaft.ExpenseTracker.model.TransactionType;

@Component
public class TransactionValidator {

    public void validateAmount(BigDecimal amount) throws InvalidTransactionException {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidTransactionException("Transaction's amount must be greater than zero");
        }
    }

    public void validateDescription(String description) throws InvalidTransactionException {
        if (description == null || description.isBlank()) {
            throw new InvalidTransactionException("Transaction's description cannot be empty");
        }
    }

    public void validateType(TransactionType type) throws InvalidTransactionException {
        if(type != TransactionType.EXPENSE
        && type != TransactionType.INCOME) {
            throw new InvalidTransactionException("Transaction's type must be EXPENSE or INCOME");
        }
    }

    public void validateTransaction(Transaction transaction) throws InvalidTransactionException {
        validateAmount(transaction.amount());
        validateDescription(transaction.description());
        validateType(transaction.type());
    }
}