package com.optimissa.training.accountservice.accountRepository;

import com.optimissa.training.accountservice.Models.Account;
import com.optimissa.training.accountservice.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepository implements IAccountRepository{

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String SQL_SELECT_ALL= "SELECT * FROM user";

    public Account getAccount(int id){
        Account a = jdbcTemplate.queryForObject("SELECT ID, BALANCE, IBAN_ID, CLIENT_ID, CURRENCY_ID, ACTIVE FROM banksucursal.ACCOUNT where id = ?",
                new Object[]{id}, new AccountMapper());
        return a;
    }
}
