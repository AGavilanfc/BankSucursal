package com.optimissa.training.userservice.repository;

import com.optimissa.training.userservice.model.Auth;
import com.optimissa.training.userservice.model.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

public interface UserRepository {

    List<User> selectAll();

    List<User> selectAllActive();

    List<User> selectAllInactive();

    User selectById(int id);

    User selectByEmail(String email);

    List<User> selectByStartWith(String select, String data);

    public List<User> getUserBylimits(int limit, int page);

    public int getUserBylimitsCount();

    int insert(User user);

    int update(User user, int id);

    int delete(int id);

    User authenticate(Auth auth);

    User verifyPassword(int id, String encryptedString);

    int updateAuthentication(User user, int id);
}
