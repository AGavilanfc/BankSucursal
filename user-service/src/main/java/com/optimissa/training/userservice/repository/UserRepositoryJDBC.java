package com.optimissa.training.userservice.repository;

import com.optimissa.training.userservice.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryJDBC implements UserRepository {

    private final Logger logger = LoggerFactory.getLogger(UserRepositoryJDBC.class);

    private static final String SQL_SELECT_ALL = "SELECT * FROM USER";
    private static final String SQL_SELECT_ALL_ACTIVE = "SELECT * FROM USER WHERE active = 1";
    private static final String SQL_SELECT_ALL_INACTIVE = "SELECT * FROM USER WHERE active = 0";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM USER WHERE id = ?";
    private static final String SQL_SELECT_BY_EMAIL = "SELECT * FROM USER WHERE email = ?";

    private static final String SQL_SELECT_BY_StartWith = "SELECT * FROM USER WHERE name LIKE ?";

    private static final String SQL_INSERT = "INSERT INTO USER (name, last_name1, last_name2, email, phone) " +
            "VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE USER SET name = ?, last_name1 = ?, last_name2 = ?, email = ?, phone = ? " +
            "WHERE id = ?";
    private static final String SQL_DELETE = "UPDATE USER SET active = 0 WHERE id = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<User> selectAll() {

        return jdbcTemplate.query(
                SQL_SELECT_ALL,
                new BeanPropertyRowMapper<>(User.class)
        );
    }

    @Override
    public List<User> selectAllActive() {
        return jdbcTemplate.query(
                SQL_SELECT_ALL_ACTIVE,
                new BeanPropertyRowMapper<>(User.class)
        );
    }

    @Override
    public List<User> selectAllInactive() {
        return jdbcTemplate.query(
                SQL_SELECT_ALL_INACTIVE,
                new BeanPropertyRowMapper<>(User.class)
        );
    }

    @Override
    public User selectById(int id) {
        return jdbcTemplate.queryForObject(
                SQL_SELECT_BY_ID,
                new BeanPropertyRowMapper<>(User.class),
                id
        );
    }

    @Override
    public User selectByEmail(String email) {
        return jdbcTemplate.queryForObject(
                SQL_SELECT_BY_EMAIL,
                new BeanPropertyRowMapper<>(User.class),
                email
        );
    }
    @Override
    public List<User> selectByStartWith(String select, String data) {

        logger.info("pepe  {} ---- {} {}", SQL_SELECT_BY_StartWith, data,select);


        return jdbcTemplate.query(
                "SELECT * FROM USER WHERE "+ select +" LIKE ?",
                new BeanPropertyRowMapper<>(User.class),
                data+"%"
        );
    }

    @Override
    public List<User> getUserBylimits(int limit, int page) {
        int offset = (page-1)*limit;
        String query = "SELECT * FROM USER LIMIT ? OFFSET ?";

        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(User.class),limit,offset);

    }

    public int getUserBylimitsCount() {
        String query = "SELECT COUNT(*) FROM USER";
        return jdbcTemplate.queryForObject(query, Integer.class);

    }




    @Override
    public int insert(User user) {
        return jdbcTemplate.update(SQL_INSERT,
                user.getName(),
                user.getLastName1(),
                user.getLastName2(),
                user.getEmail(),
                user.getPhone()
        );
    }

    @Override
    public int update(User user, int id) {
        return jdbcTemplate.update(SQL_UPDATE,
                user.getName(),
                user.getLastName1(),
                user.getLastName2(),
                user.getEmail(),
                user.getPhone(),
                id
        );
    }

    @Override
    public int delete(int id) {
        return jdbcTemplate.update(SQL_DELETE, id);
    }
}
