package com.optimissa.training.productservice.repository;

import com.optimissa.training.productservice.model.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcProductRepository implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public int save(Product product, String formattedDate) {

        return jdbcTemplate.update("INSERT INTO PRODUCT (name, active_date) values (?, ?)", product.getName(), formattedDate);
    }

    @Override
    public List<Product> findAll() {
        return jdbcTemplate.query("SELECT * FROM PRODUCT", new RowMapper<Product>() {
            @Override
            public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setActive(rs.getBoolean("active"));
                product.setActiveDate(rs.getDate("active_date"));
                product.setInactiveDate(rs.getDate("inactive_date"));
                return product;
            }
        });
    }

    @Override
    public Product findById(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM PRODUCT WHERE id = ?", new RowMapper<Product>() {
                    @Override
                    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Product product = new Product();
                        product.setId(rs.getInt("id"));
                        product.setName(rs.getString("name"));
                        product.setActive(rs.getBoolean("active"));
                        product.setActiveDate(rs.getDate("active_date"));
                        product.setInactiveDate(rs.getDate("inactive_date"));
                        return product;
                    }
                }, id);
    }

    @Override
    public List<Product> findByName(String name) {
        return jdbcTemplate.query("SELECT * FROM PRODUCT WHERE name = ?", new RowMapper<Product>() {
            @Override
            public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setActive(rs.getBoolean("active"));
                product.setActiveDate(rs.getDate("active_date"));
                product.setInactiveDate(rs.getDate("inactive_date"));
                return product;
            }
        }, name);
    }

    @Override
    public List<Product> findByActive() {
        return jdbcTemplate.query("SELECT * FROM PRODUCT WHERE active = true", new RowMapper<Product>() {
            @Override
            public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setActive(rs.getBoolean("active"));
                product.setActiveDate(rs.getDate("active_date"));
                product.setInactiveDate(rs.getDate("inactive_date"));
                return product;
            }
        });
    }

    @Override
    public List<Product> findByInactive() {
        return jdbcTemplate.query("SELECT * FROM PRODUCT WHERE active = false", new RowMapper<Product>() {
            @Override
            public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setActive(rs.getBoolean("active"));
                product.setActiveDate(rs.getDate("active_date"));
                product.setInactiveDate(rs.getDate("inactive_date"));
                return product;
            }
        });
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update("UPDATE PRODUCT SET ACTIVE = 0, INACTIVE_DATE = CURRENT_DATE() WHERE id = ?", id);
    }

    @Override
    public int update(int id, Product product) {
            String query = "UPDATE PRODUCT SET NAME = ? WHERE ID = ?";
            return jdbcTemplate.update(query, product.getName(), id);
    }
}

