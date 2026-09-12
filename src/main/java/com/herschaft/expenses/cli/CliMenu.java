package com.herschaft.expenses.cli;

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

        while (running) {

            System.out.println(
                    "======CLI Menu======\n\t1. Registrar novo gasto\n\t2. Registrar novo ganho\n\t3. Listar transações\n\t4. Genrenciar transações\n\tq. Fechar programa");

            switch (scanner.nextLine()) {
                case "1":
                    repository.save(getExpenseInfoCli(scanner));
                    break;

                case "2":
                    repository.save(getIncomeInfoCli(scanner));
                    break;

                case "3":
                    listTransactionsCli(repository.getAll(20, 20));
                    break;

                case "4":
                    selectManage(scanner);
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
        String description;
        System.out.print("Valor: ");
        double amount = readPositiveDouble(scanner);
        System.out.print("Descrição: ");
        description = scanner.nextLine();

        return new Expense(amount, description);
    }

    public static Income getIncomeInfoCli(Scanner scanner) {
        String description;
        System.out.print("Valor: ");
        double amount = readPositiveDouble(scanner);
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

    public static void listTransactions(List<Transaction> transactions) {
        for (int i = 0; i<transactions.size();i++) {
            System.out.println(String.format("===========\nOpção %d:\n\tValor: %.2f\n\tDescrição: %s\n\tTipo: %s",
                i+1,
                transactions.get(i).getAmount(),
                transactions.get(i).getDescription(),
                transactions.get(i).getTransactionTypeString())
            );
        }
    }
    
    public void selectManage(Scanner scanner) {
        List<Transaction> buffer = repository.getAll(20, 20);
        listTransactions(buffer);
        System.out.println("===========\nSelecionar qual transação?");
        int option = ((int)readPositiveDouble(scanner));
        System.out.println(
                "===========\nGostaria de mudar o que?\n\t1. Quantia.\n\t2. Descrição\n\t3. Tipo de transação\n\t4. Deletar transação");
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

            case "4":
                sendDeleteTransaction(option, buffer);
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
        double amount = readPositiveDouble(scanner);
        repository.updateAmountTransaction(
            buffer.get(option - 1).getId(),
            amount
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

    public void sendDeleteTransaction(int option, List<Transaction> buffer) {
        repository.delete(buffer.get(option - 1).getId());
    }

    public static double readPositiveDouble(Scanner scanner) {
        double amount = 0;
        boolean validAmount = false;

        System.out.print("Valor: ");
        while(!validAmount) {
            if(!scanner.hasNextDouble()) {
                System.out.println("\nDeve ser um numero maior que 0! ex: 10 5.11 5.00 9.9");
                scanner.next();
                System.out.print("Valor: ");
                continue;
            }
            amount = scanner.nextDouble();
            if(amount <= 0) {
                System.out.println("Apenas números maiores que 0!");
                System.out.print("Valor: ");
                continue;
            }

            validAmount = true;

        }
        scanner.nextLine();
        return amount;
    }

}