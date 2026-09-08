package com.herschaft.expenses.persistence;

import java.util.ArrayList;
import java.util.List;
import com.herschaft.expenses.model.Transaction;
import com.herschaft.expenses.model.TransactionType;

public class InMemoryTransactionRepository implements TransactionRepository {

    private final List<Transaction> history = new ArrayList<>();

    @Override
    public List<Transaction> getAll() {
        return history;
    }

    @Override
    public void save(Transaction transaction) {
        history.add(transaction);
    }

    @Override
    public void updateAmountTransaction(int id, double new_value) {
        
    }

    @Override
    public void updateDescriptionTransaction(int id, String new_value) {
        
    }

    @Override
    public void updateTypeTransaction(int id, TransactionType new_value) {
        
    }

}
