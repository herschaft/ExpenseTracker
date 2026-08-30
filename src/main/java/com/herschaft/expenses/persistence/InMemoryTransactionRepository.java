package com.herschaft.expenses.persistence;

import java.util.ArrayList;
import java.util.List;
import com.herschaft.expenses.model.Transaction;

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

}
