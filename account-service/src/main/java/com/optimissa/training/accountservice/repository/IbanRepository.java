package com.optimissa.training.accountservice.repository;

import com.optimissa.training.accountservice.models.Iban;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Random;

@Repository
public class IbanRepository implements IibanRepository {

    private final String GET_IBAN_BY_ID = "SELECT ID, COUNTRY_ID, COUNTRY_CONTROL, ENTITY_ID, BRANCH, ACCOUNT_CONTROL , ACCOUNT_NUMBER FROM banksucursal.IBAN where id = ?";
    private final String ADD_NEW_IBAN = "INSERT INTO IBAN (country_id, country_control ,entity_id ,branch ,account_control,account_number) VALUES (?,?,?,?,?,?)";

    private final String GET_LAST_ID= "SELECT ID FROM IBAN ORDER BY ID DESC LIMIT 1";

    @Autowired
    JdbcTemplate jdbcTemplate;

    public IbanRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Iban getIban(int id) {
        return jdbcTemplate.queryForObject(GET_IBAN_BY_ID,
                new Object[]{id}, Iban.class);

    }

    public int save(int countryId , int entityId, int branch, int accountControl, int accountNumber, int countryControl) {

        jdbcTemplate.update(ADD_NEW_IBAN, countryId, countryControl,
                                entityId, branch, accountControl, accountNumber);
        return jdbcTemplate.queryForObject(GET_LAST_ID, Integer.class);
    }


    public void delete(Iban iban, int id) {
        String sql = "UPDATE IBAN SET id = ?, country_id = ?, country_control = ?, entity_id = ?, branch = ? ,account_control  = ? , account_number = ?    WHERE id = ?";
        jdbcTemplate.update(sql, iban.getId(), iban.getCountry_id(), iban.getCountry_control(), iban.getEntity_id(), iban.getBranch(), iban.getAccount_control(), iban.getAccount_number());
    }

}
