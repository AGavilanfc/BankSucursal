package com.optimissa.training.fundservice.repository;

import com.optimissa.training.fundservice.model.Fund;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

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
        return jdbcTemplate.update("INSERT INTO FUND (NAME, REF_NUMBER, CURRENCY_ID) VALUES(?,?,?)", fund.getName(), fund.getRefNumber (), fund.getCurrencyId());
    }

    @Override
    public List<Fund> findAll() {
        return jdbcTemplate.query("select * from FUND", new DataClassRowMapper<>(Fund.class));
    }

    @Override
    public Fund findById(int id) {
        return jdbcTemplate.queryForObject("select * from FUND where id = ?", new DataClassRowMapper<>(Fund.class), id);
    }

    @Override
    public List<Fund> findByName(String name) {
        return jdbcTemplate.query("select * from FUND where name = ?", new DataClassRowMapper<>(Fund.class), name);
    }

    @Override
    public Fund findByRefNumber(String refNumber) {
        return jdbcTemplate.queryForObject("select * from FUND where ref_Number = ?", new DataClassRowMapper<>(Fund.class), refNumber);
    }

    @Override
    public List<Fund> findByCurrencyId(int currencyId) {
        return jdbcTemplate.query("select * from FUND where currency_Id = ?", new DataClassRowMapper<>(Fund.class), currencyId);
    }

    @Override
    public List<Fund> findByInactive() {
        return jdbcTemplate.query("select * from FUND where active = false", new DataClassRowMapper<>(Fund.class));
    }

    @Override
    public List<Fund> findByActive() {
        return jdbcTemplate.query("select * from FUND where active = true", new DataClassRowMapper<>(Fund.class));
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update("update FUND set ACTIVE = 0, INACTIVE_DATE = CURRENT_DATE() where id = ?", id);
    }

    @Override
    public int update(int id, Fund fund) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("name", fund.getName())
                .addValue("refNumber",fund.getRefNumber())
                .addValue("currencyId", fund.getCurrencyId())
                .addValue("active", fund.isActive());
        String query = "UPDATE FUND SET NAME = :name, " +
                "REF_NUMBER = :refNumber, " +
                "CURRENCY_ID = :currencyId, " +
                "ACTIVE = :active " +
                "WHERE ID = :id";
        return namedJdbc.update(query, params);
    }

}







