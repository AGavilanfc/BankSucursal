package com.optimissa.training.userservice.service;

import com.optimissa.training.userservice.model.User;
import com.optimissa.training.userservice.repository.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepositoryImpl userRepository;

    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    public User getUser(Integer id) {
        return userRepository.getUser(id);
    }
}
