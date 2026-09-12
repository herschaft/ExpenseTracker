package com.herschaft.expenses.persistence;

import java.util.List;
import com.herschaft.expenses.model.*;

public interface TransactionRepository {

    List<Transaction> getAll();
    void save(Transaction transaction);
    void updateAmountTransaction(int id, double new_value);
    void updateDescriptionTransaction(int id, String new_value);
    void updateTypeTransaction(int id, TransactionType new_value);
    void delete(int id);
}