package com.optimissa.training.accountservice.mapper;

import com.optimissa.training.accountservice.models.Account;
import com.optimissa.training.accountservice.models.Country;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryMapper implements RowMapper<Country> {
    @Override
    public Country mapRow(ResultSet rs, int rowNum) throws SQLException {
        Country country = new Country();
        country.setId(rs.getInt("ID"));
        country.setCode(rs.getString("CODE"));
        country.setName(rs.getString("NAME"));

        return country;
    }
}
