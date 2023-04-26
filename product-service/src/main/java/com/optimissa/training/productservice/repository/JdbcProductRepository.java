package com.optimissa.training.productservice.repository;

import com.optimissa.training.productservice.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcProductRepository implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedJdbc;

    public JdbcProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public int save(Product product) {
        return jdbcTemplate.update("INSERT INTO PRODUCT (name) values (?)", product.getName());
    }

    @Override
    public List<Product> findAll() {
        return jdbcTemplate.query("SELECT * FROM PRODUCT", new DataClassRowMapper<>(Product.class));
    }

    @Override
    public Product findById(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM PRODUCT WHERE id = ?", new DataClassRowMapper<>(Product.class),id);
    }

    @Override
    public List<Product> findByName(String name) {
        return jdbcTemplate.query("SELECT * FROM PRODUCT WHERE name = ?", new DataClassRowMapper<>(Product.class), name);
    }

    @Override
    public List<Product> findByActive() {
        return jdbcTemplate.query("SELECT * FROM PRODUCT WHERE active = true", new DataClassRowMapper<>(Product.class));
    }

    @Override
    public List<Product> findByInactive() {
        return jdbcTemplate.query("SELECT * FROM PRODUCT WHERE active = false", new DataClassRowMapper<>(Product.class));
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update("UPDATE PRODUCT SET ACTIVE = 0 WHERE id = ?", id);
    }

    @Override
    public int update(int id, Product product) {
        // TODO fix
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("name", product.getName())
                .addValue("active", product.getActive());
        String query = "UPDATE PRODUCT SET NAME = :name, " + "ACTIVE = :active " + "WHERE ID = :id";
        return namedJdbc.update(query, params);
    }
}
