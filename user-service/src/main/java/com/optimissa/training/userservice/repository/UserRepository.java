package com.optimissa.training.userservice.repository;

import com.optimissa.training.userservice.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {

    List<User> getUsers();
    User getUser(int id);
}
