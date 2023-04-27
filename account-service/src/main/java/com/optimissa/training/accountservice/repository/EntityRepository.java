package com.optimissa.training.accountservice.repository;

import com.optimissa.training.accountservice.models.Entity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EntityRepository implements IentityRepository{

    private final JdbcTemplate jdbcTemplate;

    public EntityRepository(JdbcTemplate jdbcTemplate) {
            this.jdbcTemplate = jdbcTemplate;

        }


    @Override
    public List<Entity> getAll() {
        return jdbcTemplate.query("SELECT * FROM ENTITY", new BeanPropertyRowMapper<>(Entity.class));
    }
}
