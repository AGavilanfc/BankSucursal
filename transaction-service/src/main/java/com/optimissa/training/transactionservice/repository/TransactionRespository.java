package com.optimissa.training.transactionservice.repository;

import com.optimissa.training.transactionservice.dtos.Transactions;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransactionRespository {

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

    public Transactions getByIdTransaction(int id) {
        String sql = "SELECT * FROM TRANSACTION WHERE id = ?";

        return jdbcTemplate.queryForObject(
                sql, new Object[]{id}, Transactions.class
        );



    }


    }
