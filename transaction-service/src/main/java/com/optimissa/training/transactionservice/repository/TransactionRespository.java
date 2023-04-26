package com.optimissa.training.transactionservice.repository;

import com.optimissa.training.transactionservice.dtos.Transaction;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransactionRespository implements ITransactionRepository {

    private final JdbcTemplate jdbcTemplate;


    public TransactionRespository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String getSaludo(String nombre) {
        return "¡Hola, " + nombre + "!";
    }


    public List<Transaction> getAllTransactions() {
        String sql = "SELECT * FROM TRANSACTION";

        return jdbcTemplate.query(
                sql, new BeanPropertyRowMapper<>(Transaction.class)
        );
    }

    public Transaction getByIdTransaction(int id) {
        String sql = "SELECT * FROM TRANSACTION WHERE id = ?";

        return jdbcTemplate.queryForObject(
                sql, new BeanPropertyRowMapper<>(Transaction.class), id
        );


    }

    public int insertNewTransaction(Transaction transaction, String transaction_number) {
        String sql = "INSERT INTO TRANSACTION (DATE, TRANSACTION_NUMBER, AMOUNT, ACCOUNT_ID, FUND_ID) VALUES (NOW(), ?, ?, ?, ?);";

        return jdbcTemplate.update(sql, transaction_number, transaction.getAmount(), transaction.getAccount_Id(), transaction.getFund_Id());

    }
}
