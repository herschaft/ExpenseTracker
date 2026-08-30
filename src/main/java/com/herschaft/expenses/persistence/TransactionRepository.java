package com.herschaft.expenses.persistence;

import java.util.List;
import com.herschaft.expenses.model.*;

public interface TransactionRepository {

    List<Transaction> getAll();
    void save(Transaction transaction);
    
}