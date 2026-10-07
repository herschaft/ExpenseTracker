package com.herschaft.ExpenseTracker.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
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

    public Transaction save(Transaction transaction) {
        validator.validateTransaction(transaction);
        Long id = repo.save(transaction);
        return new Transaction(transaction.amount(), transaction.description(), transaction.type(), id);
    }

    public int delete(Long id) {
        return repo.delete(id);
    }

    public int updateAmount(Long id, BigDecimal amount) {
        validator.validateAmount(amount);
        return repo.updateAmount(id, amount);
    }

    public int updateDescription(Long id, String description) {
        validator.validateDescription(description);
        return repo.updateDescription(id, description);
    }

    public int updateType(Long id, TransactionType type) {
        validator.validateType(type);
        return repo.updateType(id, type);
    }

    public List<Transaction> getList(int limit, int offset) {
        return repo.getList(limit, offset);
    }

    public Transaction getById(Long id) {
        try {
            return repo.getById(id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public boolean updateDTO(Long id, UpdateTransactionDTO dto) {
        if (getById(id) == null) {
            return false;
        }

        if (dto.amount() != null) {
            updateAmount(id, dto.amount());
        }

        if (dto.description() != null) {
            updateDescription(id, dto.description());
        }

        if (dto.type() != null) {
            updateType(id, dto.type());
        }

        return true;
    }

}
