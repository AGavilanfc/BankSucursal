package com.optimissa.training.countryservice.repository;
import com.optimissa.training.countryservice.models.country;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class countryRepository  {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<country> getAll() {
        String query = "SELECT * FROM COUNTRY";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(country.class));
    }
    public country findById(int id) {
        String query = "SELECT * FROM COUNTRY WHERE id = ?";
        return jdbcTemplate.queryForObject(query,new BeanPropertyRowMapper<>(country.class), id);
    }

}
