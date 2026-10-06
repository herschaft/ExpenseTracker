package com.herschaft.ExpenseTracker.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.herschaft.ExpenseTracker.DTO.UpdateTransactionDTO;
import com.herschaft.ExpenseTracker.model.Transaction;
import com.herschaft.ExpenseTracker.service.TransactionService;

@RestController
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @GetMapping("/transactions")
    public List<Transaction> getListTransactions(
            @RequestParam(defaultValue = "20") int limit,
            @RequestParam(defaultValue = "0") int offset) {
        return this.service.getList(limit, offset);
    }

    @GetMapping("/transactions/{id}")
    public Transaction getTransaction(@PathVariable Long id) {
        return this.service.getById(id);
    }

    @PostMapping("/transactions")
    public void createTransaction(@RequestBody Transaction transaction) {
        this.service.save(transaction);
    }

    @DeleteMapping("/transactions/{id}")
    public void deleteTransaction(@PathVariable Long id) {
        this.service.delete(id);
    }

    @PatchMapping("/transactions/{id}")
    public void updateTransaction(@PathVariable Long id, @RequestBody UpdateTransactionDTO dto) {
        this.service.updateDTO(id, dto);
    }

}
