package com.optimissa.training.userservice.repository;

import com.optimissa.training.userservice.model.User;

import java.util.List;

public interface UserRepository {

    List<User> selectAll();

    User selectById(int id);

    int insert(User user);

    int update(User user, int id);

    int delete(int id);
}
