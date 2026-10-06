package com.herschaft.ExpenseTracker.DTO;

import java.math.BigDecimal;

import com.herschaft.ExpenseTracker.model.TransactionType;

public record UpdateTransactionDTO (
    BigDecimal amount,
    String description,
    TransactionType type
) {}
