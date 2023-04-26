package com.optimissa.training.accountservice.repository;

import com.optimissa.training.accountservice.models.Country;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CountryRepository implements ICountryRepository {

    private final JdbcTemplate jdbcTemplate;

    public CountryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

    }

    @Override
    public List<Country> getAll() {

        return jdbcTemplate.queryForList("SELECT * FROM COUNTRY", Country.class);
    }
}
