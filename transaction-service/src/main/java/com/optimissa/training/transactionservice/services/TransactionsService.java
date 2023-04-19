package com.optimissa.training.transactionservice.services;

import com.optimissa.training.transactionservice.dtos.Transactions;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.util.List;

@Service
public class TransactionsService {

    private final EntityManagerFactoryBuilder entityManagerFactoryBuilder;


    private final JdbcTemplate jdbcTemplate;

    public TransactionsService(EntityManagerFactoryBuilder entityManagerFactoryBuilder, JdbcTemplate jdbcTemplate) {
        this.entityManagerFactoryBuilder = entityManagerFactoryBuilder;
        this.jdbcTemplate = jdbcTemplate;
    }

    public String getSaludo(String nombre) {
        return "¡Hola, " + nombre + "!";
    }

    public List<Transactions> getAllTransactions() {
        String sql = "SELECT * FROM transaction";
        RowMapper<Transactions> rowMapper = new BeanPropertyRowMapper<>(Transactions.class);
        int result = jdbcTemplate.queryForObject(
                "SELECT * FROM transaction", Integer.class);
        return jdbcTemplate.query(sql, rowMapper);


    }

}
