package com.optimissa.training.accountservice.repository;

import com.optimissa.training.accountservice.mapper.AccountMapper;
import com.optimissa.training.accountservice.mapper.IbanMapper;
import com.optimissa.training.accountservice.models.Account;
import com.optimissa.training.accountservice.models.Iban;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class IbanRepository implements IibanRepository{

    @Autowired
    JdbcTemplate jdbcTemplate;

    public IbanRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Iban getIban(int id){
        Iban iban = jdbcTemplate.queryForObject("SELECT ID, COUNTRY_ID, COUNTRY_CONTROL, ENTITY_ID, BRANCH, ACCOUNT_CONTROL , ACCOUNT_NUMBER FROM banksucursal.IBAN where id = ?",
                new Object[]{id}, new IbanMapper());
        return iban;

    }

    public Iban save(Iban iban) {
        String sql = "INSERT INTO IBAN (id,country_id, country_control ,entity_id ,branch ,account_control,account_number) VALUES (?,?,?,?,?)";
        jdbcTemplate.update(sql, iban.getId(), iban.getCountry_id(), iban.getCountry_control(),iban.getEntity_id(), iban.getBranch(), iban.getAccount_control(), iban.getAccount_number());
        return iban;
    }


    public void delete(Iban iban ,int id) {
        String sql = "UPDATE IBAN SET id = ?, country_id = ?, country_control = ?, entity_id = ?, branch = ? ,account_control  = ? , account_number = ?    WHERE id = ?";
        jdbcTemplate.update(sql, iban.getId(), iban.getCountry_id(), iban.getCountry_control(),iban.getEntity_id(), iban.getBranch(), iban.getAccount_control(), iban.getAccount_number());
    }

}
