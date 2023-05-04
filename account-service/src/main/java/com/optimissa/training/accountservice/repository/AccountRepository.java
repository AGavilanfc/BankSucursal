package com.optimissa.training.accountservice.repository;

import com.optimissa.training.accountservice.mapper.AccountMapper;
import com.optimissa.training.accountservice.models.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AccountRepository implements IAccountRepository {

    private final JdbcTemplate jdbcTemplate;

    private final String GET_BALANCE = "SELECT BALANCE FROM ACCOUNT WHERE id = ?";
    private final String VALUE_ACTIVE = "SELECT ACTIVE FROM ACCOUNT WHERE id = ?";
    private final String GET_IBAN_ENTITY = "SELECT E.CODE FROM ENTITY E WHERE E.ID = ?";
    private final String GET_IBAN_COUNTRY = "SELECT C.CODE FROM COUNTRY C WHERE C.ID = ?";
    private final String GET_CURRENCY_DATA = "SELECT M.NAME , M.SYMBOL , M.CODE FROM CURRENCY M WHERE M.ID=?";
    public AccountRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

    }

    public List<Account> getAllAccount() {
        return jdbcTemplate.query("SELECT * FROM ACCOUNT",
                new Object[]{}, new AccountMapper());
    }


    //CAMBIAR
    public Account getAccount(int id) {
        Account account = jdbcTemplate.queryForObject("SELECT ID, BALANCE, IBAN_ID, CLIENT_ID, CURRENCY_ID, ACTIVE ,IBAN  FROM banksucursal.ACCOUNT where id = ?",
                new Object[]{id}, new AccountMapper());

        //jdbcTemplate.queryForObject(GET_CURRENCY_DATA, new Object[]{currency_id}, Integer.class);

        return account;

    }

    public List<Account> getAccountByClient(int clientid) {
        return jdbcTemplate.query("SELECT ID, BALANCE, IBAN_ID, CLIENT_ID,CURRENCY_ID,  ACTIVE , IBAN FROM banksucursal.ACCOUNT where CLIENT_ID = ?",
                new Object[]{clientid}, new AccountMapper());
    }


    public int save(Account account) {
        String sql = "INSERT INTO ACCOUNT ( balance,iban_id ,client_id,currency_id,active,iban) VALUES (?,?,?,?,?,?)";
        return jdbcTemplate.update(sql, account.getBalance(), account.getIban_id(), account.getClient_id(), account.getCurrency_id(), true,account.getIban());

    }

    public int delete(int id) {
        //num de datos que se han actualizado
        String sql = "UPDATE ACCOUNT SET active = ?  WHERE id = ?";
        return jdbcTemplate.update(sql, false, id);
    }

    public int updateAddBalance(int id, double balance){

        double balanceActual= jdbcTemplate.queryForObject(GET_BALANCE , new Object[]{id}, Double.class);
        boolean isActive = jdbcTemplate.queryForObject(VALUE_ACTIVE,new Object[]{id}, Boolean.class);
        double balanceFinal = 0;

        if(balance>0 && isActive){
            balanceFinal = balanceActual + balance;
            String sql = "UPDATE ACCOUNT SET balance = ? WHERE id = ?";
            jdbcTemplate.update(sql, balanceFinal, id);
        }

        return 0;
    }

    @Override
    public int updateBalanceSubstract(int id, double balance) {

        double balanceActual= jdbcTemplate.queryForObject(GET_BALANCE , new Object[]{id}, Double.class);
        boolean isActive = jdbcTemplate.queryForObject(VALUE_ACTIVE,new Object[]{id}, Boolean.class);
        double balanceFinal = 0;

        if(balance>0 && isActive && balance<=balanceActual){
            balanceFinal = balanceActual - balance;
            String sql = "UPDATE ACCOUNT SET balance = ? WHERE id = ?";
            jdbcTemplate.update(sql, balanceFinal, id);
        }

        return 0;
    }

    @Override
    public int detIBANEntity(int ibanEntityId) {
        return jdbcTemplate.queryForObject(GET_IBAN_ENTITY, new Object[]{ibanEntityId}, Integer.class);
    }

    @Override
    public String getibanCountry(int ibanCountryId) {
        return jdbcTemplate.queryForObject(GET_IBAN_COUNTRY, new Object[]{ibanCountryId}, String.class);
    }


}

