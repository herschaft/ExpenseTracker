package com.herschaft.expenses.database;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.herschaft.expenses.model.TransactionType;
import com.herschaft.expenses.model.Transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class Database {

    final String databaseSqlFolder = "jdbc:sqlite:resources/transactions.db";

    public List<Transaction> list() {
        try (
            Connection connection = DriverManager.getConnection(databaseSqlFolder);
            Statement statement = connection.createStatement();
        ) {
            List<Transaction> list = new ArrayList<>();
            ResultSet result = statement.executeQuery("SELECT * FROM transactions");
            while(result.next()) {
                list.add(new Transaction(result.getInt("id"), result.getDouble("amount"), result.getString("description"), TransactionType.valueOf(result.getString("transaction_type"))));
            }
            return list;
        } catch (SQLException e) {
            List<Transaction> list = new ArrayList<>();
            e.printStackTrace();
            return list;
        }
    }

    public void updateAmountTransaction(double new_value, int id) {
        try (
            Connection connection = DriverManager.getConnection(databaseSqlFolder);
            PreparedStatement statement = connection.prepareStatement("""
                UPDATE transactions
                SET amount = ?
                WHERE id = ?
                """
            );
        ) {
            statement.setDouble(1, new_value);
            statement.setInt(2, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDescriptionTransaction(String new_value, int id) {
        try (
            Connection connection = DriverManager.getConnection(databaseSqlFolder);
            PreparedStatement statement = connection.prepareStatement("""
                UPDATE transactions
                SET description = ?
                WHERE id = ?
                """
            );
        ) {
            statement.setString(1, new_value);
            statement.setInt(2, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTypeTransaction(TransactionType new_value, int id) {
        try (
            Connection connection = DriverManager.getConnection(databaseSqlFolder);
            PreparedStatement statement = connection.prepareStatement("""
                UPDATE transactions
                SET transaction_type = ?
                WHERE id = ?
                """
            );
        ) {
            statement.setString(1, new_value.name());
            statement.setInt(2, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void save(double amount, String description, TransactionType type) {
        try (
            Connection connection = DriverManager.getConnection(databaseSqlFolder);
            PreparedStatement statement = connection.prepareStatement("""
                INSERT INTO transactions (amount, description, transaction_type) VALUES (?, ?, ?)
            """);
         ) {
            statement.setDouble(1, amount);
            statement.setString(2, description);
            statement.setString(3, type.name());

            statement.executeUpdate();
         } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try (
            Connection connection = DriverManager.getConnection(databaseSqlFolder);
            PreparedStatement statement = connection.prepareStatement("""
                    DELETE
                    FROM transactions
                    WHERE id == ?
                    """);
        ) {
            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean tableExists() {
        try (
                Connection connection = DriverManager.getConnection(databaseSqlFolder);
                Statement statement = connection.createStatement();) {
            ResultSet result = statement.executeQuery("""
                        SELECT name
                        FROM sqlite_master
                        WHERE type = 'table'
                        AND name = 'transactions';
                    """);

            return result.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean createDB() {
        try (
            Connection connection = DriverManager.getConnection(databaseSqlFolder);
            Statement statement = connection.createStatement();
        ) {
                statement.execute("""
                    CREATE TABLE IF NOT EXISTS transactions (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    amount DOUBLE NOT NULL,
                    description TEXT,
                    transaction_type TEXT NOT NULL
                        CHECK (transaction_type IN ('INCOME', 'EXPENSE')),
                    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
                    );
                """);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}