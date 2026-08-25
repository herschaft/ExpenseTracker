package com.herschaft.expenses;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        List<Transaction> history = new ArrayList<>();
        history.add(new Expense(5, "salgado"));

        double saldo = 0;

        Transaction trans;
        Scanner scanner = new Scanner(System.in);
        double valor = 0;
        String descrição = "";
        boolean negativo = false;

        System.out.println("Valor: ");
        valor = Double.parseDouble(scanner.next());
        System.out.println("Descrição: ");
        descrição = scanner.next();
        System.out.println("Negativo? y/n ");
        if(scanner.next().equalsIgnoreCase("y")) { negativo = true; }
        else { negativo = false; }

        if(negativo) { trans = new Expense(valor, descrição); }
        else { trans = new Income(valor, descrição); }

        history.add(trans);
        
        
        for (Transaction transactions : history) {
            System.out.println(String.format("===========\nValor: %.2f\nDescrição: %s\nNegativo: %s",
            transactions.getAmount(),
            transactions.getDescription(),
            transactions.getTransactioTypeString()
            ));

            saldo += transactions.getSignedAmount();

        }

        System.out.println(String.format("\n\nValor final: %.2f", saldo));

        scanner.close();

    }

}