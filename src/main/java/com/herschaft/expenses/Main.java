package com.herschaft.expenses;

import com.herschaft.expenses.cli.CliMenu;
import com.herschaft.expenses.persistence.SqliteTransactionRepository;

public class Main {

    public static void main(String[] args) {
        SqliteTransactionRepository repository = new SqliteTransactionRepository();
        CliMenu menu = new CliMenu(repository);

        menu.start();
    }
}