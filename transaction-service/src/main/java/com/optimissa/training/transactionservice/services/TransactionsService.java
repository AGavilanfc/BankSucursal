package com.optimissa.training.transactionservice.services;

import com.optimissa.training.transactionservice.dtos.Transactions;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionsService {



    private final JdbcTemplate jdbcTemplate;

    public TransactionsService(JdbcTemplate jdbcTemplate) {
        //this.entityManagerFactoryBuilder = entityManagerFactoryBuilder;
        this.jdbcTemplate = jdbcTemplate;
    }

    public String getSaludo(String nombre) {
        return "¡Hola, " + nombre + "!";
    }

    public List<Transactions> getAllTransactions() {
        String sql = "SELECT * FROM transaction";

        return jdbcTemplate.query(
                sql,
                (rs, rowNum) ->
                        new Transactions(
                                rs.getInt("id"),
                                rs.getString("date"),
                                rs.getString("transaction_Number"),
                                rs.getDouble("amount"),
                                rs.getInt("account_Id"),
                                rs.getInt("fund_Id")
                        )
        );
    }
}
