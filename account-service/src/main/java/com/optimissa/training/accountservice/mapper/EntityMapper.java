package com.optimissa.training.accountservice.mapper;

import com.optimissa.training.accountservice.models.Entity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EntityMapper implements RowMapper<Entity> {
    //clase para asignar los valores de las columnas del registro a un objeto cuenta
    @Override
    public Entity mapRow(ResultSet rs, int rowNum) throws SQLException {
        Entity entity = new Entity();
        entity.setId(rs.getInt("ID"));
        entity.setCode(rs.getInt("CODE"));
        entity.setName(rs.getString("NAME"));

        return entity;
    }
}
