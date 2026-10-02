    package com.herschaft.ExpenseTracker.validation;

    import com.herschaft.ExpenseTracker.exception.InvalidTransactionException;

    public class TransactionValidator {

        public void validateAmount(Double amount) throws InvalidTransactionException {
            if (amount == null || amount <= 0) {
                throw new InvalidTransactionException("Transaction amount must be greater than zero");
            }
        }

        public void validateDescription(String description) throws InvalidTransactionException {
            if (description == null || description.isBlank()) {
                throw new InvalidTransactionException("Transaction description cannot be empty");
            }
        }

    }
