package com.herschaft.ExpenseTracker.persistence;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.herschaft.ExpenseTracker.model.User;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbc;

    public UserRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public boolean checkUsername(String username) {

        return jdbc.queryForObject(
            """
            SELECT EXISTS (
                SELECT 1 FROM users WHERE username = ?
            )
            """,
            Boolean.class,
            username
        );

    }

    public void registerNewUser(User user) {
        jdbc.update(
            """
            INSERT INTO users (username, password_hash, uuid)
            VALUES (?, ?, ?)
            """,
            user.username(),
            user.passwordHash(),
            user.uuid()
        );
    }

}
