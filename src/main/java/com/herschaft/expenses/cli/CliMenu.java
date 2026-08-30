package com.herschaft.expenses.cli;

import java.util.List;
import java.util.Scanner;

import com.herschaft.expenses.model.Expense;
import com.herschaft.expenses.model.Income;
import com.herschaft.expenses.model.Transaction;
import com.herschaft.expenses.persistence.TransactionRepository;

public class CliMenu {

    private final TransactionRepository repository;

    public CliMenu(TransactionRepository repository) {
        this.repository = repository;
    }

    public void start() {

        Scanner scanner = new Scanner(System.in);

        boolean running = true;

        while (running) {

            System.out.println(
                    "======CLI Menu======\n\t1. Registrar novo gasto\n\t2. Registrar novo ganho\n\t3. Listar transações\n\tq. Fechar programa");

            switch (scanner.nextLine()) {
                case "1":
                    repository.save(getExpenseInfoCli(scanner));
                    break;

                case "2":
                    repository.save(getIncomeInfoCli(scanner));
                    break;

                case "3":
                    listTransactionsCli(repository.getAll());
                    break;

                case "q":
                    running = false;
                    break;

                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }

        scanner.close();

    }

    public static Expense getExpenseInfoCli(Scanner scanner) {
        double amount;
        String description;
        System.out.print("Valor: ");
        amount = Double.parseDouble(scanner.nextLine());
        System.out.print("Descrição: ");
        description = scanner.nextLine();
        return new Expense(amount, description);
    }

    public static Income getIncomeInfoCli(Scanner scanner) {
        double amount;
        String description;
        System.out.print("Valor: ");
        amount = Double.parseDouble(scanner.nextLine());
        System.out.print("Descrição: ");
        description = scanner.nextLine();
        return new Income(amount, description);
    }

    public static void listTransactionsCli(List<Transaction> transactions) {
        for (Transaction transaction : transactions) {
            System.out.println(String.format("===========\nValor: %.2f\nDescrição: %s",
                    transaction.getAmount(),
                    transaction.getDescription()));
        }
    }
}