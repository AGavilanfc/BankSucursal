package com.optimissa.training.fundservice.repository;

import com.optimissa.training.fundservice.model.Fund;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class JdbcRepository implements FundRepository {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public JdbcRepository(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public int save(Fund fund) { return jdbcTemplate.update("INSERT INTO FUND (NAME, REF_NUMBER, CURRENCY_ID) VALUES(?,?,?)", fund.getName(), fund.getRefNumber (), fund.getCurrencyId());
    }

    @Override
    public List<Fund> findAll() { return jdbcTemplate.query("select * from FUND", new DataClassRowMapper<>(Fund.class));
    }

    @Override
    public Fund findById(int id) { return jdbcTemplate.queryForObject("select * from FUND where id = ?", new DataClassRowMapper<>(Fund.class), id);
    }

    @Override
    public List<Fund> findByName(String name) { return jdbcTemplate.query("select * from FUND where name = ?", new DataClassRowMapper<>(Fund.class), name);
    }

    @Override
    public Fund findByRefNumber(String refNumber) { return jdbcTemplate.queryForObject("select * from FUND where ref_Number = ?", new DataClassRowMapper<>(Fund.class), refNumber);
    }

    @Override
    public List<Fund> findByCurrencyId(int currencyId) { return jdbcTemplate.query("select * from FUND where currency_Id = ?", new DataClassRowMapper<>(Fund.class), currencyId);
    }

    @Override
    public List<Fund> findByActive(boolean active) { return jdbcTemplate.query("select * from FUND where active = ?", new DataClassRowMapper<>(Fund.class), active);
    }

    @Override
    public int delete(int id) { return jdbcTemplate.update("update FUND set active = 1 where id = ?", id);
    }

    @Override
    public int update(Fund fund, int id) { return jdbcTemplate.update("update FUND set name = ?, ref_Number = ?, currency_Id = ? where id = ?",fund.getName(),fund.getRefNumber(),fund.getCurrencyId(), id);
    }

}







