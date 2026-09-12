package com.herschaft.expenses.persistence;

import java.util.ArrayList;
import java.util.List;
import com.herschaft.expenses.model.Transaction;
import com.herschaft.expenses.model.TransactionType;

public class InMemoryTransactionRepository implements TransactionRepository {

    private final List<Transaction> history = new ArrayList<>();

    public List<Transaction> getAll() {
        return history;
    }

    public void save(Transaction transaction) {
        history.add(transaction);
    }

    public void updateAmountTransaction(int id, double new_value) {
        //TODO
    }

    public void updateDescriptionTransaction(int id, String new_value) {        
        //TODO
    }

    public void updateTypeTransaction(int id, TransactionType new_value) {        
        //TODO
    }

    public void delete(int id) {
        //TODO
    }

}
