package com.optimissa.training.fundservice.repository;

import com.optimissa.training.fundservice.model.Fund;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
public class JdbcRepository implements FundRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedJdbc;

    public JdbcRepository(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public int save(Fund fund) {
        return jdbcTemplate.update("INSERT INTO FUND (NAME, REF_NUMBER, CURRENCY_ID, ACTIVE_DATE) VALUES(?,?,?, CURRENT_DATE)", fund.getName(), fund.getRefNumber (), fund.getCurrencyId());
    }

    @Override
    public List<Fund> findAll() {
        return jdbcTemplate.query("select * from FUND", new RowMapper<Fund>() {
            @Override
            public Fund mapRow(ResultSet rs, int rowNum) throws SQLException {
                Fund fund = new Fund();
                fund.setId(rs.getInt("id"));
                fund.setName(rs.getString("name"));
                fund.setRefNumber(rs.getString("ref_number"));
                fund.setCurrencyId(rs.getInt("currency_id"));
                fund.setActive(rs.getBoolean("active"));
                fund.setActiveDate(rs.getDate("active_date"));
                fund.setInactiveDate(rs.getDate("inactive_date"));
                return fund;
            }
        });
    }

    @Override
    public Fund findById(int id) {
        return jdbcTemplate.queryForObject("select * from FUND where id = ?", new RowMapper<Fund>() {
            @Override
            public Fund mapRow(ResultSet rs, int rowNum) throws SQLException {
                Fund fund = new Fund();
                fund.setId(rs.getInt("id"));
                fund.setName(rs.getString("name"));
                fund.setRefNumber(rs.getString("ref_number"));
                fund.setCurrencyId(rs.getInt("currency_id"));
                fund.setActive(rs.getBoolean("active"));
                fund.setActiveDate(rs.getDate("active_date"));
                fund.setInactiveDate(rs.getDate("inactive_date"));
                return fund;
            }
        }, id);
    }

    @Override
    public List<Fund> findByName(String name) {
        return jdbcTemplate.query("select * from FUND where name = ?", new RowMapper<Fund>() {
            @Override
            public Fund mapRow(ResultSet rs, int rowNum) throws SQLException {
                Fund fund = new Fund();
                fund.setId(rs.getInt("id"));
                fund.setName(rs.getString("name"));
                fund.setRefNumber(rs.getString("ref_number"));
                fund.setCurrencyId(rs.getInt("currency_id"));
                fund.setActive(rs.getBoolean("active"));
                fund.setActiveDate(rs.getDate("active_date"));
                fund.setInactiveDate(rs.getDate("inactive_date"));
                return fund;
            }
        }, name);
    }

    @Override
    public Fund findByRefNumber(String refNumber) {
        return jdbcTemplate.queryForObject("select * from FUND where ref_number = ?", new RowMapper<Fund>() {
            @Override
            public Fund mapRow(ResultSet rs, int rowNum) throws SQLException {
                Fund fund = new Fund();
                fund.setId(rs.getInt("id"));
                fund.setName(rs.getString("name"));
                fund.setRefNumber(rs.getString("ref_number"));
                fund.setCurrencyId(rs.getInt("currency_id"));
                fund.setActive(rs.getBoolean("active"));
                fund.setActiveDate(rs.getDate("active_date"));
                fund.setInactiveDate(rs.getDate("inactive_date"));
                return fund;
            }
        }, refNumber);
    }

    @Override
    public List<Fund> findByCurrencyId(int currencyId) {
        return jdbcTemplate.query("select * from FUND where currency_id = ?", new RowMapper<Fund>() {
            @Override
            public Fund mapRow(ResultSet rs, int rowNum) throws SQLException {
                Fund fund = new Fund();
                fund.setId(rs.getInt("id"));
                fund.setName(rs.getString("name"));
                fund.setRefNumber(rs.getString("ref_number"));
                fund.setCurrencyId(rs.getInt("currency_id"));
                fund.setActive(rs.getBoolean("active"));
                fund.setActiveDate(rs.getDate("active_date"));
                fund.setInactiveDate(rs.getDate("inactive_date"));
                return fund;
            }
        }, currencyId);
    }

    @Override
    public List<Fund> findByInactive() {
        return jdbcTemplate.query("select * from FUND where active = false", new RowMapper<Fund>() {
            @Override
            public Fund mapRow(ResultSet rs, int rowNum) throws SQLException {
                Fund fund = new Fund();
                fund.setId(rs.getInt("id"));
                fund.setName(rs.getString("name"));
                fund.setRefNumber(rs.getString("ref_number"));
                fund.setCurrencyId(rs.getInt("currency_id"));
                fund.setActive(rs.getBoolean("active"));
                fund.setActiveDate(rs.getDate("active_date"));
                fund.setInactiveDate(rs.getDate("inactive_date"));
                return fund;
            }
        });
    }

    @Override
    public List<Fund> findByActive() {
        return jdbcTemplate.query("select * from FUND where active = true", new RowMapper<Fund>() {
            @Override
            public Fund mapRow(ResultSet rs, int rowNum) throws SQLException {
                Fund fund = new Fund();
                fund.setId(rs.getInt("id"));
                fund.setName(rs.getString("name"));
                fund.setRefNumber(rs.getString("ref_number"));
                fund.setCurrencyId(rs.getInt("currency_id"));
                fund.setActive(rs.getBoolean("active"));
                fund.setActiveDate(rs.getDate("active_date"));
                fund.setInactiveDate(rs.getDate("inactive_date"));
                return fund;
            }
        });
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update("update FUND set ACTIVE = 0, INACTIVE_DATE = CURRENT_DATE() where id = ?", id);
    }

    @Override
    public int update(int id, Fund fund) {
        String query = "update FUND set NAME = ?, REF_NUMBER = ?, CURRENCY_ID = ? where id = ?";
        return jdbcTemplate.update(query, fund.getName(), fund.getRefNumber(), fund.getCurrencyId(), id);
    }

}







