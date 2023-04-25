package com.optimissa.training.accountservice.repository;

import com.optimissa.training.accountservice.models.Account;
import com.optimissa.training.accountservice.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public abstract class AccountRepository implements IAccountRepository{


    private JdbcTemplate jdbcTemplate;

    public AccountRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Account getAccount(int id){
        Account account = jdbcTemplate.queryForObject("SELECT ID, BALANCE, IBAN_ID, CLIENT_ID, CURRENCY_ID, ACTIVE FROM banksucursal.ACCOUNT where id = ?",
                new Object[]{id}, new AccountMapper());
        return account;
    }

    //update

    public Account save(Account account) {
        String sql = "INSERT INTO ACCOUNT (id, balance, client_id,currency_id,active) VALUES (?,?,?,?,?)";
        jdbcTemplate.update(sql, account.getId(), account.getBalance(), account.getClient_id(), account.getCurrency_id(), account.isActive());
        return account;
    }


    public void delete(int id) {
        String sql = "UPDATE ACCOUNT SET active = ?  WHERE id = ?";
        jdbcTemplate.update(sql, false, id );
    }



}

