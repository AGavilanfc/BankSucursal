package com.optimissa.training.accountservice.repository;

import com.optimissa.training.accountservice.mapper.AccountMapper;
import com.optimissa.training.accountservice.models.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountRepository implements IAccountRepository {

    private final JdbcTemplate jdbcTemplate;

    public AccountRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

    }

    public List<Account> getAllAccount() {
        return jdbcTemplate.query("SELECT * FROM ACCOUNT",
                new Object[]{}, new AccountMapper());
    }

    public Account getAccount(int id) {
        Account account = jdbcTemplate.queryForObject("SELECT ID, BALANCE, IBAN_ID, CLIENT_ID, CURRENCY_ID, ACTIVE FROM banksucursal.ACCOUNT where id = ?",
                new Object[]{id}, new AccountMapper());
        return account;
    }

    public int save(Account account) {
        String sql = "INSERT INTO ACCOUNT ( balance,iban_id ,client_id,currency_id,active) VALUES (?,?,?,?,?)";
        return jdbcTemplate.update(sql, account.getBalance(), account.getIban_id(), account.getClient_id(), account.getCurrency_id(), true);
    }

    public int delete(int id) {
        //num de datos que se han actualizado
        String sql = "UPDATE ACCOUNT SET active = ?  WHERE id = ?";
        return jdbcTemplate.update(sql, false, id);
    }


    public int update(Account account, int id) {
        //se puede cambiar el balance
        String sql = "UPDATE ACCOUNT SET  balance = ?  WHERE id = ? ";
        return jdbcTemplate.update(sql, account.getBalance(), id);
    }
}

