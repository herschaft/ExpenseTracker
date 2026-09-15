package com.herschaft.expenses.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public List<Transaction> getListTransactions(@RequestParam(defaultValue = "20") int limit, @RequestParam(defaultValue = "0") int offset) {
        return this.repository.getAll(limit, offset);
    }

    @GetMapping("/transactions/{id}")
    public Transaction getTransaction(@PathVariable int id) {
        return this.repository.getTransaction(id);
    }
    
    @PostMapping("/transactions")
    public void createTransaction(@RequestBody Transaction transaction) {
        this.repository.save(transaction);
    }

    @DeleteMapping("/transactions/{id}")
    public void deleteTransaction(@PathVariable int id) {
        this.repository.delete(id);
    }

    @PatchMapping("/transactions/{id}")
    public void updateTransaction(@PathVariable int id) {
        
    }

}
