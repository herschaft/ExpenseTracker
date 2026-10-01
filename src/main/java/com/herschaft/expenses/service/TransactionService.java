package com.herschaft.expenses.service;

import java.lang.reflect.Field;

import com.herschaft.expenses.model.Transaction;
import com.herschaft.expenses.persistence.TransactionRepository;

public class TransactionService {

    private TransactionRepository repository;

    public TransactionService(TransactionRepository repository){
        this.repository = repository;
    }

    /*
    Transactions to be considered valid rules:
        general: cannot be an null or empty object
        id: cannot be created if isn't by the persistence
        amount: Double not less than 0
        description: String not empty
        transaction_type: String "INCOME" or "EXPENSE"
    */
    public String createTransaction(Transaction transaction) {

        if(transaction.equals(null)) {
            return "Empty/Null object";
        }

        for (Field field : transaction.getClass().getDeclaredFields()) {
            field.setAccessible(true);

            try {
                if (field.get(transaction) == null) {
                    return String.format("%s empty", field.getName());
                }
                switch (field) {
                    case Transaction.class.getField("amount"):
                        transaction.getAmount
                        break;
                
                    default:
                        break;
                }
            } catch (IllegalAccessException e) {
                return "Could not access field: " + field.getName();
            }
        }

        this.repository.save(transaction);

        return "Everything Ok";
    }

}
