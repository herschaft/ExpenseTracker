package com.herschaft.expenses;

import com.herschaft.expenses.cli.CliMenu;
import com.herschaft.expenses.persistence.InMemoryTransactionRepository;
import com.herschaft.expenses.persistence.TransactionRepository;

public class Main {

    public static void main(String[] args) {
        TransactionRepository repository = new InMemoryTransactionRepository();
        CliMenu menu = new CliMenu(repository);

        menu.start();
    }
}