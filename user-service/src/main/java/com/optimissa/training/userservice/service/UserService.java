package com.optimissa.training.userservice.service;

import com.optimissa.training.userservice.api.UserBasicResponse;
// import com.optimissa.training.userservice.api.UserResponse;
import com.optimissa.training.userservice.controller.UserController;
import com.optimissa.training.userservice.model.User;
import com.optimissa.training.userservice.repository.UserRepositoryJDBC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
// import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserService {

    // private static final String URL_USER_CLIENTS = "http://localhost:8091/clients/get-by-userId/";
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

    public User getUserById(int id) {
        logger.info("Started userService.getUserById()");
        long startTime = System.currentTimeMillis();
        User user = userRepository.selectById(id);
        long endTime = System.currentTimeMillis();
        logger.info("Finished userService.getUserById(). Execution took: {}ms. Response: {}", endTime-startTime, user );
        return user;
    }


    public User getUserByEmail(String email) {
        logger.info("Started userService.getUserById()");
        long startTime = System.currentTimeMillis();
        User user = userRepository.selectByEmail(email);
        long endTime = System.currentTimeMillis();
        logger.info("Finished userService.getUserById(). Execution took: {}ms. Response: {}", endTime-startTime, user.toString() );
        return user;
    }

    public List<User> getUserByStartWith(String select, String data) {
        logger.info("Started userService.getUserById()");
        long startTime = System.currentTimeMillis();
        List<User> user = userRepository.selectByStartWith(select, data);
        long endTime = System.currentTimeMillis();
        logger.info("Finished userService.getUserById(). Execution took: {}ms. Response: {}", endTime-startTime, user.toString() );
        return user;
    }

    public User getUserToLogging(String data){
        logger.info("Started userService.getUserById()");
        long startTime = System.currentTimeMillis();
        User user = userRepository.selectToLogging(data);
        long endTime = System.currentTimeMillis();
        logger.info("Finished userService.getUserById(). Execution took: {}ms. Response: {}", endTime-startTime, user.toString());
        return user;
    }

    public Object getUserBylimits(int limit, int page) {
        long startTime = System.currentTimeMillis();
        List<User> users = userRepository.getUserBylimits(limit, page);

        int totalElements = userRepository.getUserBylimitsCount();
        int totalPages = (int) Math.ceil((double) totalElements / 10);

        Map<String, Object> result = new HashMap<>();
        result.put("totalPages", totalPages);
        result.put("users", users);

        long endTime = System.currentTimeMillis();
        logger.info("Finished userService.getUserBylimits(). Execution took: {}ms. Response: {}", endTime-startTime, users.toString() );
        return result;
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

    public int updateUserStatus(int id, int activate) {
        logger.info("Started userService.updateUser()");
        long startTime = System.currentTimeMillis();
        int affectedRows = userRepository.updateUserStatus(id,activate);
        long endTime = System.currentTimeMillis();
        logger.info("Finished userService.updateUser(). Execution took: {}ms. Response: affectedRows = {}", endTime-startTime, affectedRows );
        return affectedRows;
    }

}
