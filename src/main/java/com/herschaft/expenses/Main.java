package com.herschaft.expenses;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //Initialize list of transactions and added example 
        List<Transaction> history = new ArrayList<>();
        history.add(new Expense(5, "salgado"));

        Scanner scanner = new Scanner(System.in);

        System.out.println("======CLI Menu======\n\t1. Registrar novo gasto\n\t2. Registrar novo ganho\n\t3. Listar transações\n\tq. Fechar programa");
        switch (scanner.next()) {
            case "1":
                history.add(getExpenseInfoCli(scanner));
                //Debug placeholder until better flow
                listTransactionsCli(history);
                break;
            case "2":
                history.add(getIncomeInfoCli(scanner));
                //Debug placeholder until better flow
                listTransactionsCli(history);
                break;
            case "3":
                listTransactionsCli(history);
                break;
            default:
                break;
        }

        scanner.close();
    }

    public static Expense getExpenseInfoCli(Scanner scanner) {
        double amount;
        String description;
        System.out.print("Valor: ");
        amount = Double.parseDouble(scanner.next());
        System.out.print("Descrição: ");
        description = scanner.nextLine();
        return new Expense(amount, description);
    }

    public static Income getIncomeInfoCli(Scanner scanner) {
        double amount;
        String description;
        System.out.print("Valor: ");
        amount = Double.parseDouble(scanner.next());
        System.out.print("Descrição: ");
        description = scanner.nextLine();
        return new Income(amount, description);
    }

    public static void listTransactionsCli(List<Transaction> transactions) {
        for (Transaction transaction : transactions) {
            System.out.println(String.format("===========\nValor: %.2f\nDescrição: %s\nNegativo: %s",
            transaction.getAmount(),
            transaction.getDescription(),
            transaction.getTransactionTypeString()
            ));
        }
    }
}