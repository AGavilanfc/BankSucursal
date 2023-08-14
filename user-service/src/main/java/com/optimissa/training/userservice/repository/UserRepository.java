package com.optimissa.training.userservice.repository;

import com.optimissa.training.userservice.api.ImageResponse;
import com.optimissa.training.userservice.api.UserResponAuth;
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

    List<User> getUserBylimits(int limit, int page);

     int getUserBylimitsCount();

    int insert(User user);

    int update(User user, int id);

    int delete(int id);

    User authenticate(Auth auth);

    boolean isValidPassword(int id, String encryptedString);

    int updateAuthentication(String email, String password);

    ImageResponse selectImageById(int id);

    int updateImageUserById(ImageResponse imageResponse);

    int insertImageUser(ImageResponse imageResponse);
}
