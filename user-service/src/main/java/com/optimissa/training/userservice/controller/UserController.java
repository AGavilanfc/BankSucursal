package com.optimissa.training.userservice.controller;

import com.optimissa.training.userservice.model.User;
import com.optimissa.training.userservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {


    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @GetMapping("")
    public List<User> getUsers() {
        List<User> users = null;
        logger.info("Inside method getUsers()");
        long startTime = System.currentTimeMillis();
        users = userService.getUsers();
        long endTime = System.currentTimeMillis();
        logger.info("Call to userService.getUsers(). Response: {}. Execution took: {}ms", users.toString(), endTime-startTime);
        return users;
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable int id) {
        User user = null;
        logger.info("Inside method getUser()");
        long startTime = System.currentTimeMillis();
        user = userService.getUser(id);
        long endTime = System.currentTimeMillis();
        logger.info("Call to userService.getUser(). Response: {}. Execution took: {}ms", user.toString(), endTime-startTime);
        return user;
    }

    @PostMapping()
    public int addUser(@RequestBody User user) {
        int response = 0;
        logger.info("Inside method addUser()");
        long startTime = System.currentTimeMillis();
        response = userService.addUser(user);
        long endTime = System.currentTimeMillis();
        logger.info("Call to userService.addUser(). Response: Affected rows {}. Execution took: {}ms", response, endTime-startTime);
        return response;
    }

    @PutMapping("/{id}")
    public int modifyUser(@RequestBody User user, @PathVariable int id) {
        int response = 0;
        logger.info("Inside method modifyUser()");
        long startTime = System.currentTimeMillis();
        response = userService.modifyUser(user, id);
        long endTime = System.currentTimeMillis();
        logger.info("Call to userService.modifyUser(). Response: Affected rows {}. Execution took: {}ms", response, endTime-startTime);
        return response;
    }

    @DeleteMapping("/{id}")
    public int removeUser(@PathVariable int id) {
        int response = 0;
        logger.info("Inside method removeUser()");
        long startTime = System.currentTimeMillis();
        response = userService.removeUser(id);
        long endTime = System.currentTimeMillis();
        logger.info("Call to userService.removeUser(). Response: Affected rows {}. Execution took: {}ms", response, endTime-startTime);
        return response;
    }

}
