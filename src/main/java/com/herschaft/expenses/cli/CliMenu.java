package com.herschaft.expenses.cli;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.herschaft.expenses.model.Expense;
import com.herschaft.expenses.model.Income;
import com.herschaft.expenses.model.Transaction;
import com.herschaft.expenses.model.TransactionType;
import com.herschaft.expenses.persistence.TransactionRepository;

public class CliMenu {

    private final TransactionRepository repository;

    public CliMenu(TransactionRepository repository) {
        this.repository = repository;
    }

    public void start() {

        Scanner scanner = new Scanner(System.in);

        boolean running = true;

        List<Transaction> buffer = new ArrayList<>();

        while (running) {

            System.out.println(
                    "======CLI Menu======\n\t1. Registrar novo gasto\n\t2. Registrar novo ganho\n\t3. Listar transações\n\t4. Mudar transação\n\tq. Fechar programa");

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

                case "4":
                    selectUpdate(scanner, buffer);
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
            System.out.println(String.format("===========\nValor: %.2f\nDescrição: %s\nTipo: %s",
                transaction.getAmount(),
                transaction.getDescription(),
                transaction.getTransactionTypeString())
            );
        }
    }

    public static void listTransactionsUpdate(List<Transaction> transactions) {
        for (int i = 0; i<transactions.size();i++) {
            System.out.println(String.format("===========\nOpção %d:\n\tValor: %.2f\n\tDescrição: %s\n\tTipo: %s",
                i+1,
                transactions.get(i).getAmount(),
                transactions.get(i).getDescription(),
                transactions.get(i).getTransactionTypeString())
            );
        }
    }
    
    public void selectUpdate(Scanner scanner, List<Transaction>buffer) {
        buffer = repository.getAll();
        listTransactionsUpdate(buffer);
        System.out.println("===========\nSelecionar qual transação?");
        int option = Integer.parseInt(scanner.nextLine());
        System.out.println(
                "===========\nGostaria de mudar o que?\n\t1. Quantia.\n\t2. Descrição\n\t3. Tipo de transação");
        switch (scanner.nextLine()) {
            case "1":
                askNewAmount(scanner, option, buffer);
                break;

            case "2":
                askNewDescription(scanner, option, buffer);
                break;

            case "3":
                askNewType(scanner, option, buffer);
                break;

            default:
                break;
        }
    }

    public void askNewDescription(Scanner scanner, int option, List<Transaction> buffer) {
        System.out.print("Nova Descrição: ");
        repository.updateDescriptionTransaction(buffer.get(option - 1).getId(), scanner.nextLine());
    }

    public void askNewAmount(Scanner scanner, int option, List<Transaction> buffer) {
        System.out.print("Novo Valor: ");
        repository.updateAmountTransaction(
            buffer.get(option - 1).getId(),
            Double.parseDouble(scanner.nextLine())
        );
    }

    public void askNewType(Scanner scanner, int option, List<Transaction> buffer) {
        System.out.print("Mudar para gasto ou ganho? ");
        String type = scanner.nextLine();
        if (type.equals("gasto")) {
            repository.updateTypeTransaction(buffer.get(option - 1).getId(), TransactionType.EXPENSE);
        } else if (type.equals("ganho")) {
            repository.updateTypeTransaction(buffer.get(option - 1).getId(), TransactionType.INCOME);
        } else {
            System.out.println("Opção invalida!");
            askNewType(scanner, option, buffer);
        }
    }

}