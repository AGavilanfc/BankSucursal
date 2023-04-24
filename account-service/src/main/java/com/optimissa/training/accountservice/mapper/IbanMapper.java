package com.optimissa.training.accountservice.mapper;

import com.optimissa.training.accountservice.models.Account;
import com.optimissa.training.accountservice.models.IbanModel;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IbanModelMapper implements RowMapper<IbanModel> {


    public IbanModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        IbanModel ibanModel =  new IbanModel();
        ibanModel.setId(rs.getInt("ID"));
        ibanModel.setCountry_id(rs.getInt("COUNTRY_ID"));
        ibanModel.setCountry_control(rs.getInt("COUNTRY_CONTROL"));
        ibanModel.setEntity_id(rs.getInt("ENTITY_ID"));
        ibanModel.setBranch(rs.getInt("BRANCH"));
        ibanModel.setAccount_control(rs.getInt("ACCOUNT_CONTROL"));
        ibanModel.setAccount_number(rs.getInt("ACCOUNT_NUMBER"));

        return ibanModel;
    }
}
