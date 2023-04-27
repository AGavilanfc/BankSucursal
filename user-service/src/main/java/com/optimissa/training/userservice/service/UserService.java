package com.optimissa.training.userservice.service;

import com.optimissa.training.userservice.api.UserBasicResponse;
import com.optimissa.training.userservice.api.UserResponse;
import com.optimissa.training.userservice.controller.UserController;
import com.optimissa.training.userservice.model.User;
import com.optimissa.training.userservice.repository.UserRepositoryJDBC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private static final String URL_CLIENT = "http://localhost:8091/clients";
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserRepositoryJDBC userRepository;

    public List<User> getUsers() {
        logger.info("Started userService.getUsers()");
        long startTime = System.currentTimeMillis();
        List<User> users = userRepository.selectAll();
        long endTime = System.currentTimeMillis();
        logger.info("Finished userService.getUsers(). Execution took: {}ms. Response: {}", endTime-startTime, users.toString() );
        return users;
    }

    public List<UserBasicResponse> getActiveUsers() {
        logger.info("Started userService.getActiveUsers()");
        long startTime = System.currentTimeMillis();
        List<User> users = userRepository.selectAllActive();
        List<UserBasicResponse> usersBasicResponse = new ArrayList<>();
        users.forEach(user -> usersBasicResponse.add(new UserBasicResponse(user.getName() + " " + user.getLastName1(), user.getEmail())));
        long endTime = System.currentTimeMillis();
        logger.info("Finished userService.getActiveUsers(). Execution took: {}ms. Response: {}", endTime-startTime, usersBasicResponse );
        return usersBasicResponse;
    }

    public List<User> getInactiveUsers() {
        logger.info("Started userService.getInactiveUsers()");
        long startTime = System.currentTimeMillis();
        List<User> users = userRepository.selectAllInactive();
        long endTime = System.currentTimeMillis();
        logger.info("Finished userService.getInactiveUsers(). Execution took: {}ms. Response: {}", endTime-startTime, users.toString() );
        return users;
    }

    public UserResponse getUserById(int id) {
        logger.info("Started userService.getUserById()");
        long startTime = System.currentTimeMillis();
        User user = userRepository.selectById(id);
        UserResponse userResponse = new UserResponse(user.getName(), user.getEmail(), user.getPhone());
        RestTemplate restTemplate = new RestTemplate();
        Object[] userClients =  restTemplate.getForObject(URL_CLIENT+"/get-by-user/" + user.getId(), Object[].class);
        if (userClients != null) userResponse.setClients(userClients);
        long endTime = System.currentTimeMillis();
        logger.info("Finished userService.getUserById(). Execution took: {}ms. Response: {}", endTime-startTime, userResponse );
        return userResponse;
    }

    public User getUserByEmail(String email) {
        logger.info("Started userService.getUserById()");
        long startTime = System.currentTimeMillis();
        User user = userRepository.selectByEmail(email);
        long endTime = System.currentTimeMillis();
        logger.info("Finished userService.getUserById(). Execution took: {}ms. Response: {}", endTime-startTime, user.toString() );
        return user;
    }

    public int addUser(User user) {
        logger.info("Started userService.addUser()");
        long startTime = System.currentTimeMillis();
        int affectedRows = userRepository.insert(user);
        long endTime = System.currentTimeMillis();
        logger.info("Finished userService.addUser(). Execution took: {}ms. Response: affectedRows = {}", endTime-startTime, affectedRows );
        return affectedRows;
    }

    public int modifyUser(User user, int id) {
        logger.info("Started userService.modifyUser()");
        long startTime = System.currentTimeMillis();
        int affectedRows = userRepository.update(user, id);
        long endTime = System.currentTimeMillis();
        logger.info("Finished userService.modifyUser(). Execution took: {}ms. Response: affectedRows = {}", endTime-startTime, affectedRows );
        return affectedRows;
    }

    public int removeUser(int id) {
        logger.info("Started userService.removeUser()");
        long startTime = System.currentTimeMillis();
        int affectedRows = userRepository.delete(id);
        long endTime = System.currentTimeMillis();
        logger.info("Finished userService.removeUser(). Execution took: {}ms. Response: affectedRows = {}", endTime-startTime, affectedRows );
        return affectedRows;
    }

}
