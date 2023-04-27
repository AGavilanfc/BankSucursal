package com.optimissa.training.accountservice.repository;

import com.optimissa.training.accountservice.mapper.AccountMapper;
import com.optimissa.training.accountservice.models.Account;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountRepository implements IAccountRepository {

    private final JdbcTemplate jdbcTemplate;

    private final String GET_BALANCE = "SELECT BALANCE FROM ACCOUNT WHERE id = ?";
    private final String VALUE_ACTIVE = "SELECT ACTIVE FROM ACCOUNT WHERE id = ?";
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

    public List<Account> getAccountByClient(int clientid) {
        return jdbcTemplate.query("SELECT ID, BALANCE, IBAN_ID, CLIENT_ID,CURRENCY_ID,  ACTIVE FROM banksucursal.ACCOUNT where CLIENT_ID = ?",
                new Object[]{clientid}, new AccountMapper());
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


//    public int update(Account account, int id) {
//        String sql = "UPDATE ACCOUNT SET  balance = ?  WHERE id = ? ";
//        return jdbcTemplate.update(sql, account.getBalance(), id);
//    }

    public int updateAddBalance(int id, double balance){

        double balanceActual= jdbcTemplate.queryForObject(GET_BALANCE , new Object[]{id}, Double.class);
        boolean isActive = jdbcTemplate.queryForObject(VALUE_ACTIVE,new Object[]{id}, Boolean.class);

        double balanceFinal = 0;


        if(balanceActual>0 && isActive){
            balanceFinal = balanceActual + balance;
        }


        String sql = "UPDATE ACCOUNT SET balance = ? WHERE id = ?";
        return jdbcTemplate.update(sql, balanceFinal, id);

    }

    //poner el dinero en la cuenta (ID, DINERO>0):
    // 1. comrobar si existe la cuenta y si esta activa
    //2. Mirar cuanto dinero hay en la cuenTa
    //3. sumar lo que nos pasan con el dinero existente
    // 4. guardar el la bbdd el nuevo valor

}

