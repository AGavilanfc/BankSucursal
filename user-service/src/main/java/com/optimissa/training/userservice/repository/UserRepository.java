package com.optimissa.training.userservice.repository;

import com.optimissa.training.userservice.model.User;

import java.util.List;

public interface UserRepository {

    List<User> selectAll();

    List<User> selectAllActive();

    List<User> selectAllInactive();

    User selectById(int id);

    User selectByEmail(String email);

    int insert(User user);

    int update(User user, int id);

    int delete(int id);
}
