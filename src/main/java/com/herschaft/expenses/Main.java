package com.herschaft.expenses;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Transaction> history = new ArrayList<>();
        history.add(new Transaction(20, "Pai deu dinheiro pro busão", false));
        history.add(new Transaction(10.60, "Busão pro centro", true));
        history.add(new Transaction(11.50, "Cigarrinho na tabacaria", true));

        double buffer = 0;

        for (Transaction transactions : history) {
            System.out.println(String.format("===========\nValor: %.2f\nDescrição: %s\nNegativo: %s",
            transactions.getAmount(),
            transactions.getDescription(),
            transactions.getNegativeString()
            ));

            buffer += transactions.getSignedAmount();

        }

        System.out.println(String.format("\n\nValor final: %.2f", buffer));

    }

}