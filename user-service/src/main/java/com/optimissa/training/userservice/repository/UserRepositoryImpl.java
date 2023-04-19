package com.optimissa.training.userservice.repository;

import com.optimissa.training.userservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private static final String SQL_SELECT_ALL = "SELECT * FROM user";
    private static final String SQL_SELECT_ONE = "SELECT * FROM user WHERE id=?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<User> getUsers() {

        return jdbcTemplate.query(
                SQL_SELECT_ALL,
                (rs, rowNum) -> new User(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("last_name1"),
                        rs.getString("last_name2"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getBoolean("active"))
        );
    }

    @Override
    public User getUser(int id) {
        return jdbcTemplate.queryForObject(
                SQL_SELECT_ONE,
                new Object[]{id},
                (rs, rowNum) -> new User(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("last_name1"),
                        rs.getString("last_name2"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getBoolean("active"))
        );
    }
}
