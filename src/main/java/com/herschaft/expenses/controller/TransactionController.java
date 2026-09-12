package com.herschaft.expenses.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.herschaft.expenses.model.Transaction;
import com.herschaft.expenses.persistence.TransactionRepository;

@RestController 
public class TransactionController {

    private final TransactionRepository repository;

    public TransactionController(TransactionRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/transactions")
    public List<Transaction> getListTransactions(@RequestParam(defaultValue = "20", required = true) int limit, @RequestParam(defaultValue = "0", required = true) int offset) {
        return this.repository.getAll(limit, offset);
    }

    @PostMapping("/transaction")
    public void createTransaction(@RequestBody Transaction transaction) {
        this.repository.save(transaction);
    }

}
