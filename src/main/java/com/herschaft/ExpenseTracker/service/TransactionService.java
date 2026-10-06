package com.herschaft.ExpenseTracker.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.herschaft.ExpenseTracker.DTO.UpdateTransactionDTO;
import com.herschaft.ExpenseTracker.model.Transaction;
import com.herschaft.ExpenseTracker.model.TransactionType;
import com.herschaft.ExpenseTracker.persistence.TransactionRepository;
import com.herschaft.ExpenseTracker.validation.TransactionValidator;

@Service
public class TransactionService {

    private final TransactionRepository repo;
    private final TransactionValidator validator;

    public TransactionService(TransactionRepository repo, TransactionValidator validator) {
        this.repo = repo;
        this.validator = validator;
    }

    public void save(Transaction transaction) {
        validator.validateTransaction(transaction);
        repo.save(transaction);
    }

    public void delete(Long id) {
        repo.delete(id);
    }

    public void updateAmount(Long id, BigDecimal amount) {
        validator.validateAmount(amount);
        repo.updateAmount(id, amount);
    }

    public void updateDescription(Long id, String description) {
        validator.validateDescription(description);
        repo.updateDescription(id, description);
    }

    public void updateType(Long id, TransactionType type) {
        validator.validateType(type);
        repo.updateType(id, type);
    }

    public List<Transaction> getList(int limit, int offset) {
        return repo.getList(limit, offset);
    }

    public Transaction getById(Long id) {
        return repo.getById(id);
    }

    public void updateDTO(Long id, UpdateTransactionDTO dto) {
        if(dto.amount() != null) {
            updateAmount(id, dto.amount());
        }
        if(dto.description() != null) {
            updateDescription(id, dto.description());
        }
        if(dto.type() != null) {
            updateType(id, dto.type());
        }
    }

}
