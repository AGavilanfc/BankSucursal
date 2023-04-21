package com.optimissa.training.fundservice.repository;

import com.optimissa.training.fundservice.model.Fund;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcRepository implements FundRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Fund fund) {
        return jdbcTemplate.update("INSERT INTO FUND (NAME, REF_NUMBER, CURRENCY_ID) VALUES(?,?,?)",
                fund.getName(), fund.getRefNumber (), fund.getCurrencyId());

    }

    @Override
    public List<Fund> findAll() {
        return jdbcTemplate.query(
                "select * from FUND",
                (rs, rowNum) ->
                        new Fund(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("refNumber"),
                                rs.getInt("currencyId"),
                                rs.getBoolean("active")

                        )
        );
    }
}
