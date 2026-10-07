package com.herschaft.ExpenseTracker.persistence;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.herschaft.ExpenseTracker.model.Transaction;
import com.herschaft.ExpenseTracker.model.TransactionType;

@Repository 
public class TransactionRepository {

    private final JdbcTemplate jdbc;

    public TransactionRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Long save(Transaction transaction) {
        return jdbc.queryForObject(
            """
            INSERT INTO transactions (amount, description, type)
            VALUES (?, ?, ?)
            RETURNING id
            """,
            Long.class,
            transaction.amount(),
            transaction.description(),
            transaction.type().name()
        );
    }

    public List<Transaction> getList(int limit, int offset) {
        return jdbc.query(
            """
            SELECT * FROM transactions ORDER BY id LIMIT ? OFFSET ?
            """,
            (rs, i) -> new Transaction(
                rs.getBigDecimal("amount"),
                rs.getString("description"),
                TransactionType.valueOf(rs.getString("type")),
                rs.getLong("id")
            ),
            limit, offset
        );
    }

    public Transaction getById(Long id){
        return jdbc.queryForObject(
            """
            SELECT id, amount, description, type FROM transactions WHERE id = ?
            """,
            (rs, i) -> new Transaction(
                rs.getBigDecimal("amount"),
                rs.getString("description"),
                TransactionType.valueOf(rs.getString("type")),
                rs.getLong("id")
            ),
            id
        );
    }

    public int updateAmount(Long id, BigDecimal amount) {
        int rows = jdbc.update(
            """
            UPDATE transactions SET amount = ? WHERE id = ? 
            """,
            amount, id
        );

        return rows;
    }

    public int updateDescription(Long id, String description) {
        int rows = jdbc.update(
            """
            UPDATE transactions SET description = ? WHERE id = ? 
            """,
            description, id
        );

        return rows;
    }

    public int updateType(Long id, TransactionType type) {
        int rows = jdbc.update(
            """
            UPDATE transactions SET type = ? WHERE id = ? 
            """,
            type.name(), id
        );

        return rows;
    }

    public int delete(Long id) {
        int rows = jdbc.update(
            "DELETE FROM transactions WHERE id = ?",
            id
        );

        return rows;
    }
}
