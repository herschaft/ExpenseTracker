package com.herschaft.expenses.persistence;

import java.util.List;

import com.herschaft.expenses.database.Database;
import com.herschaft.expenses.model.Transaction;

public class SqliteTransactionRepository implements TransactionRepository {

    private Database db = new Database();

    public SqliteTransactionRepository() {
        init();
    }

    @Override
    public List<Transaction> getAll() {
        return db.list();
    }

    @Override
    public void save(Transaction transaction){
        db.save(transaction.getAmount(), transaction.getDescription(), transaction.getTransactionType());
    }

    public boolean init() {
        if(!db.tableExists()) {
            if(db.createDB()) {
                return true;
            }
            return false;
        }
        return false;
    }

}
