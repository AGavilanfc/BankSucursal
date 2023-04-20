package com.optimissa.training.transactionservice.repository;

import com.optimissa.training.transactionservice.dtos.Transactions;
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


    public List<Transactions> getAllTransactions() {
        String sql = "SELECT * FROM TRANSACTION";

        return jdbcTemplate.query(
                sql, new BeanPropertyRowMapper<>(Transactions.class)
        );
    }

    public Transactions getByIdTransaction(int id) {
        String sql = "SELECT * FROM TRANSACTION WHERE id = ?";

        return jdbcTemplate.queryForObject(
                sql, new BeanPropertyRowMapper<>(Transactions.class), id
        );



    }


    }
