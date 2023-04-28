package com.optimissa.training.accountservice.mapper;

import com.optimissa.training.accountservice.models.Account;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AccountMapper implements RowMapper<Account> {
    //clase para asignar los valores de las columnas del registro a un objeto cuenta
    @Override
    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
        Account account = new Account();
        account.setId(rs.getInt("ID"));
        account.setBalance(rs.getDouble("BALANCE"));
        account.setIban_id(rs.getInt("IBAN_ID"));
        account.setClient_id(rs.getInt("CLIENT_ID"));
        account.setCurrency_id(rs.getInt("CURRENCY_ID"));
        account.setActive(rs.getBoolean("ACTIVE"));
        account.setIban(rs.getString("IBAN"));
        return account;
    }
}
