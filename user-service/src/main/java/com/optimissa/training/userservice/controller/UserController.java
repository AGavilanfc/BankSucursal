package com.optimissa.training.userservice.controller;

import com.optimissa.training.userservice.api.StringResponse;
import com.optimissa.training.userservice.model.User;
import com.optimissa.training.userservice.service.UserService;
import com.optimissa.training.userservice.util.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {


    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserUtil userUtil = new UserUtil();

    @Autowired
    UserService userService;

    @GetMapping("/get-all")
    public ResponseEntity<Object> getUsers() {
        try {
            return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getCause().getMessage());
            return new ResponseEntity<>(new StringResponse(e.getCause().getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-all/active")
    public ResponseEntity<Object> getActiveUsers() {
        try {
            return new ResponseEntity<>(userService.getActiveUsers(), HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getCause().getMessage());
            return new ResponseEntity<>(new StringResponse(e.getCause().getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-all/inactive")
    public ResponseEntity<Object> getInactiveUsers() {
        try {
            return new ResponseEntity<>(userService.getInactiveUsers(), HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getCause().getMessage());
            return new ResponseEntity<>(new StringResponse(e.getCause().getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable int id) {
        try {
            return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getCause().getMessage());
            return new ResponseEntity<>(new StringResponse(e.getCause().getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-by-email/{email}")
    public ResponseEntity<Object> getUserByEmail(@PathVariable String email) {
        try {
            return new ResponseEntity<>(userService.getUserByEmail(email), HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getCause().getMessage());
            return new ResponseEntity<>(new StringResponse(e.getCause().getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<StringResponse> addUser(@RequestBody User user) {
        if (userUtil.checkEmail(user.getEmail())) {
            if (userUtil.checkPhone(user.getPhone())) {
                try {
                    if (userService.addUser(user) == 1) {
                        return new ResponseEntity<>(new StringResponse("User has been created"), HttpStatus.CREATED);
                    } else {
                        return new ResponseEntity<>(new StringResponse("User has not been created"), HttpStatus.BAD_REQUEST);
                    }
                } catch (Exception e) {
                    logger.error(e.getCause().getMessage());
                    return new ResponseEntity<>(new StringResponse(e.getCause().getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                logger.error("Wrong phone format");
                return new ResponseEntity<>(new StringResponse("Wrong phone format"), HttpStatus.BAD_REQUEST);
            }
        } else {
            logger.error("Wrong email format");
            return new ResponseEntity<>(new StringResponse("Wrong email format"), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/id/{id}")
    public ResponseEntity<StringResponse> modifyUser(@RequestBody User user, @PathVariable int id) {
        if (userUtil.checkEmail(user.getEmail())) {
            if (userUtil.checkPhone(user.getPhone())) {
                try {
                    if (userService.modifyUser(user, id) == 1) {
                        return new ResponseEntity<>(new StringResponse("User has been modified"), HttpStatus.ACCEPTED);
                    } else {
                        return new ResponseEntity<>(new StringResponse("User id does not exist"), HttpStatus.NOT_FOUND);
                    }
                } catch (Exception e) {
                    logger.error(e.getCause().getMessage());
                    return new ResponseEntity<>(new StringResponse(e.getCause().getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                logger.error("Wrong phone format");
                return new ResponseEntity<>(new StringResponse("Wrong phone format"), HttpStatus.BAD_REQUEST);
            }
        } else {
            logger.error("Wrong email format");
            return new ResponseEntity<>(new StringResponse("Wrong email format"), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/id/{id}")
    public ResponseEntity<StringResponse> removeUser(@PathVariable int id) {
        try {
            if (userService.removeUser(id) == 1) {
                return new ResponseEntity<>(new StringResponse("User has been deleted"), HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>(new StringResponse("User id does not exist"), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error(e.getCause().getMessage());
            return new ResponseEntity<>(new StringResponse(e.getCause().getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
