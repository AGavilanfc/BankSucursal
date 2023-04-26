package com.optimissa.training.accountservice.repository;

import com.optimissa.training.accountservice.models.Entity;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class EntityRepository implements IentityRepository{

    private final JdbcTemplate jdbcTemplate;

    public EntityRepository(JdbcTemplate jdbcTemplate) {
            this.jdbcTemplate = jdbcTemplate;

        }


    @Override
    public List<Entity> getAll() {
        return jdbcTemplate.queryForList("SELECT * FROM ENTITY", Entity.class);
    }
}
