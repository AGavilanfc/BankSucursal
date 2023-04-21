package com.optimissa.training.productservice.repository;

import com.optimissa.training.productservice.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcProductRepository implements ProductRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public int save(Product product) {
        return jdbcTemplate.update("INSERT INTO PRODUCT (name) values (?)", product.getName());
    }

    @Override
    public List<Product> findAll() {
        return jdbcTemplate.query("SELECT * FROM PRODUCT", (rs, rowNum) ->
                new Product(rs.getInt("id"), rs.getString("name"), rs.getBoolean("active")));
    }

    @Override
    public Product findById(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM PRODUCT WHERE id = ?", new DataClassRowMapper<>(Product.class),id);
    }


}
