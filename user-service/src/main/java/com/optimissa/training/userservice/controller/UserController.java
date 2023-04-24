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
        long startTime = System.nanoTime();
        users = userService.getUsers();
        long endTime = System.nanoTime();
        logger.info("Call to userService.getUsers(). Response:" + users.toString() +
                ". Execution took: " + (endTime-startTime)/1000000 + "ms");
        return users;
    }

    @GetMapping("/{id}")
    public User getUsers(@PathVariable int id) {
        return userService.getUser(id);
    }

    @PostMapping()
    public int addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PutMapping("/{id}")
    public int modifyUser(@RequestBody User user, @PathVariable int id) {
        return userService.modifyUser(user, id);
    }

    @DeleteMapping("/{id}")
    public int removeUser(@PathVariable int id) {
        return userService.removeUser(id);
    }

}
