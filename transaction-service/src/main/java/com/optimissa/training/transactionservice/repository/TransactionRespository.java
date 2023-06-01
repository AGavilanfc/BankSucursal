package com.optimissa.training.transactionservice.repository;

import com.optimissa.training.transactionservice.dtos.Account;
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


    public List<Transaction> getAllTransactions(int limit, int page) {
        int offset = (page-1)*limit;
        String sql = "SELECT * FROM TRANSACTION LIMIT ? OFFSET ?";

        return jdbcTemplate.query(
                sql, new BeanPropertyRowMapper<>(Transaction.class), limit, offset
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

    public List<Transaction> getAllTransactionsByAmount(int min, int max) {
        String sql = "SELECT * FROM TRANSACTION WHERE amount BETWEEN ? AND ? ORDER BY amount";

        return jdbcTemplate.query(
                sql, new BeanPropertyRowMapper<>(Transaction.class), min, max
        );
    }

    @Override
    public List<Account> getAccountBylimits(int id , int page, int limit) {
        int offset = (page-1)*limit;
        String query = "SELECT * FROM TRANSACTION WHERE ACCOUNT_ID = ? LIMIT ? OFFSET ?";

        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Account.class),id ,limit,offset);

    }

    public int getAccountBylimitsCount() {
        String query = "SELECT COUNT(*) FROM TRANSACTION";
        return jdbcTemplate.queryForObject(query, Integer.class);
    }
}
