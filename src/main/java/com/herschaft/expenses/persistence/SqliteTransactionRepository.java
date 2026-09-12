package com.herschaft.expenses.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.herschaft.expenses.database.Database;
import com.herschaft.expenses.model.Transaction;
import com.herschaft.expenses.model.TransactionType;

@Repository
public class SqliteTransactionRepository implements TransactionRepository {

    private final Database db;

    public SqliteTransactionRepository(Database db) {
        this.db = db;
        init();
    }

    @Override
    public List<Transaction> getAll(int limit, int offset) {
        return db.list(limit, offset);
    }

    @Override
    public void save(Transaction transaction){
        db.save(transaction.getAmount(), transaction.getDescription(), transaction.getTransactionType());
    }

    public void updateAmountTransaction(int id, double new_value) {
        db.updateAmountTransaction(new_value, id);
    }
    
    public void updateDescriptionTransaction(int id, String new_value) {
        db.updateDescriptionTransaction(new_value, id);
    }

    public void updateTypeTransaction(int id, TransactionType new_value) {
        db.updateTypeTransaction(new_value, id);
    }

    public void delete(int id) {
        db.delete(id);
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
