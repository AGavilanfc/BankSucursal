package com.optimissa.training.currencyservice.repository;
import com.optimissa.training.currencyservice.model.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.lang.Override;

import java.util.*;
@Repository
public class CurrencyRepository{


@Autowired
private JdbcTemplate jdbcTemplate;

    public List<Currency> findAll() {
        String query = "SELECT * FROM CURRENCY";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Currency.class));
    }


}
