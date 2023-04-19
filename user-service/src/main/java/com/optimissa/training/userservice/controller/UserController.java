package com.optimissa.training.userservice.controller;

import com.optimissa.training.userservice.model.User;
import com.optimissa.training.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public User getUsers(@PathVariable int id) {
        return userService.getUser(id);
    }

    @PostMapping()
    public void adduser(@RequestBody User user) {
        
    }

}
