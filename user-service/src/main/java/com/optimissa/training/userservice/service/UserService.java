package com.optimissa.training.userservice.service;

import com.optimissa.training.userservice.model.User;
import com.optimissa.training.userservice.repository.UserRepositoryJDBC;
import com.optimissa.training.userservice.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserUtil userUtil = new UserUtil();
    @Autowired
    UserRepositoryJDBC userRepository;

    public List<User> getUsers() {
        return userRepository.selectAll();
    }

    public User getUser(int id) {
        return userRepository.selectById(id);
    }

    public int addUser(User user) {
        return userUtil.checkEmail(user.getEmail())
                ? userRepository.insert(user)
                : 0;
    }

    public int modifyUser(User user, int id) {
        return userUtil.checkEmail(user.getEmail())
                ? userRepository.update(user,id)
                : 0;
    }

    public int removeUser(int id) {
        return userRepository.delete(id);
    }

}
