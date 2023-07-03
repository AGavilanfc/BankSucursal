package com.optimissa.training.entityservice.repository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import com.optimissa.training.entityservice.models.entity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class entityRepository implements IentityRepository{

    private final JdbcTemplate jdbcTemplate;

    public entityRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

    }


    @Override
    public List<entity> getAll() {
        return jdbcTemplate.query("SELECT * FROM ENTITY", new BeanPropertyRowMapper<>(entity.class));
    }

    public entity findById(int id) {
        String query = "SELECT * FROM ENTITY WHERE id = ?";
        return jdbcTemplate.queryForObject(query,new BeanPropertyRowMapper<>(entity.class), id);
    }
}
