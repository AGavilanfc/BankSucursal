package com.optimissa.training.userservice.repository;

import com.optimissa.training.userservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserRepositoryJDBC implements UserRepository {

    private static final String SQL_SELECT_ALL = "SELECT * FROM user";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM user WHERE id=?";
    private static final String SQL_INSERT = "INSERT INTO user (name, last_name1, last_name2, email, phone, active) " +
            "VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE user SET name=?, last_name1=?, last_name2=?, email=?, phone=? " +
            "WHERE id=?";
    private static final String SQL_DELETE = "UPDATE user SET active=0 WHERE id=?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<User> selectAll() {

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
    public User selectById(int id) {
        return jdbcTemplate.queryForObject(
                SQL_SELECT_BY_ID,
                User.class,
                id
        );
    }

    @Override
    public int insert(User user) {
        return jdbcTemplate.update(SQL_INSERT, user);
    }

    @Override
    public int update(User user, int id) {
        return jdbcTemplate.update(SQL_UPDATE, user, id);
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update(SQL_DELETE, id);
    }
}
