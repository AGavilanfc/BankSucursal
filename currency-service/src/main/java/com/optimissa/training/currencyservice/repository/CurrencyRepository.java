package com.optimissa.training.currencyservice.repository;
import com.optimissa.training.currencyservice.model.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
@Repository
public class CurrencyRepository{


@Autowired
private JdbcTemplate jdbcTemplate;

    public List<Currency> getAll() {
        String query = "SELECT * FROM CURRENCY";
        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
             List<Currency> currencies = new ArrayList<>();
             while (resultSet.next()) {
                Currency currency = new Currency();
                currencies.add(currency);
             }
             return currencies;
        } catch (SQLException e) {
            return Collections.emptyList();
        }
    }
//        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Currency.class));

    public Currency findById(int id) {
        String query = "SELECT * FROM CURRENCY WHERE id = ?";
        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Currency currency = new Currency();
                    return currency;
                } else {
                    return null;
                }
            }

        } catch (SQLException e) {
            return null;
        }

//        return jdbcTemplate.queryForObject(query,new BeanPropertyRowMapper<>(Currency.class), id);
    }

    public int  createCurrency(Currency currency) {
        String query = "INSERT INTO CURRENCY (name, symbol, code) VALUES (?, ?, ?)";

        return jdbcTemplate.update(query, currency.getName(), currency.getSymbol(), currency.getCode());
    }
    public int delete(int id) {
        String query = "DELETE FROM CURRENCY WHERE id = ?";
        return jdbcTemplate.update(query, id);
    }
    public int update(Currency currency) {
        String query = "UPDATE CURRENCY SET name = ?, symbol = ?, code = ? WHERE id = ?";
        return jdbcTemplate.update(query, currency.getName(), currency.getSymbol(), currency.getCode(), currency.getId());
    }

    public Currency getByName(String name) {
        String query = "SELECT * FROM CURRENCY WHERE NAME = ?";
        try {
            return jdbcTemplate.queryForObject(query, new Object[]{name}, new BeanPropertyRowMapper<>(Currency.class));
        } catch (Exception e) {
            return null;
        }
    }
    public Currency findByCode(String code) {
        String query = "SELECT * FROM CURRENCY WHERE CODE = ?";
        List<Currency> currencies = jdbcTemplate.query(query, new Object[]{code}, new BeanPropertyRowMapper<>(Currency.class));
        return currencies.isEmpty() ? null : currencies.get(0);
    }
}
