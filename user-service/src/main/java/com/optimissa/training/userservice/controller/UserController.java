package com.optimissa.training.userservice.controller;

import com.optimissa.training.userservice.api.ImageResponse;
import com.optimissa.training.userservice.api.StringResponse;
import com.optimissa.training.userservice.api.UserResponAuth;
import com.optimissa.training.userservice.model.Auth;
import com.optimissa.training.userservice.model.User;
import com.optimissa.training.userservice.service.UserService;
import com.optimissa.training.userservice.util.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final UserUtil userUtil = new UserUtil();
    String filePath;
    @Value("${rute.absolute.user.id.image}")
    String filePathAbsolute;

    @Autowired
    UserService userService;
    
    @GetMapping("/get-all")
    public ResponseEntity<Object> getUsers() {
        try {
            return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error(e.getCause().getMessage());
            return new ResponseEntity<>(new StringResponse(e.getCause().getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-users/{page}")
    public ResponseEntity<Object> getUserByLimits(@PathVariable int page) {
        try {
            return new ResponseEntity<>(userService.getUserByLimits(10, page), HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("Not found");
            return new ResponseEntity<>("Client not found. ", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-all/active")
    public ResponseEntity<Object> getActiveUsers() {
        try {
            return new ResponseEntity<>(userService.getActiveUsers(), HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error(e.getCause().getMessage());
            return new ResponseEntity<>(new StringResponse(e.getCause().getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-all/inactive")
    public ResponseEntity<Object> getInactiveUsers() {
        try {
            return new ResponseEntity<>(userService.getInactiveUsers(), HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error(e.getCause().getMessage());
            return new ResponseEntity<>(new StringResponse(e.getCause().getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable int id) {
        try {
            return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("Id not found");
            return new ResponseEntity<>(new StringResponse("Id not found"), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-by-email/{email}")
    public ResponseEntity<Object> getUserByEmail(@PathVariable String email) {
        try {
            return new ResponseEntity<>(userService.getUserByEmail(email), HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("Email not found");
            return new ResponseEntity<>(new StringResponse("Email not found"), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-by-StartWith/{select}/{data}")
    public ResponseEntity<Object> getUserByStartWith(@PathVariable String select, @PathVariable String data) {
        try {
            return new ResponseEntity<>(userService.getUserByStartWith(select, data), HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("Not found");
            return new ResponseEntity<>(new StringResponse("Not found"), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/authentication")
    public ResponseEntity<Object> authenticate(@RequestBody Auth auth) {
        try {
            return new ResponseEntity<>(userService.authenticate(auth), HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("USER OR PASSWORD INCORRECT");
            return new ResponseEntity<>(new StringResponse("USER OR PASSWORD INCORRECT"), HttpStatus.NOT_FOUND);
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
                    LOGGER.error(e.getCause().getMessage());
                    return new ResponseEntity<>(new StringResponse(e.getCause().getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                LOGGER.error("Wrong phone format");
                return new ResponseEntity<>(new StringResponse("Wrong phone format"), HttpStatus.BAD_REQUEST);
            }
        } else {
            LOGGER.error("Wrong email format");
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
                    LOGGER.error(e.getCause().getMessage());
                    return new ResponseEntity<>(new StringResponse(e.getCause().getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                LOGGER.error("Wrong phone format");
                return new ResponseEntity<>(new StringResponse("Wrong phone format"), HttpStatus.BAD_REQUEST);
            }
        } else {
            LOGGER.error("Wrong email format");
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
            LOGGER.error(e.getCause().getMessage());
            return new ResponseEntity<>(new StringResponse(e.getCause().getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/update/id/{id}/status/{activate}")
    public ResponseEntity<Object> updateActivateUser(@RequestBody int id, @PathVariable int activate) {
        try {
            return new ResponseEntity<>(userService.updateUserStatus(id, activate), HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error(e.getCause().getMessage());
            return new ResponseEntity<>(new StringResponse(e.getCause().getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/update/authentication")
    public ResponseEntity<StringResponse> modifyAuthenticationUser(@RequestBody UserResponAuth userResponAuth) {

        try {
            userService.modifyAuthenticationUser(userResponAuth);
            return new ResponseEntity<>(new StringResponse("User has been modified"), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            LOGGER.error(e.getCause().getMessage());
            return new ResponseEntity<>(new StringResponse(e.getCause().getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/get-image-by-id/{id}")
    public ResponseEntity<Object> getImageUserById(@PathVariable int id) {
        try {
            return new ResponseEntity<>(userService.getImageUserById(id), HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("Id not found");
            return new ResponseEntity<>(new StringResponse("Id not found"), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/set-image-by-id")
    public ResponseEntity<StringResponse> updateImageUserById(@RequestBody ImageResponse imageResponse) {

        try {
            userService.updateImageUserById(imageResponse);
            return new ResponseEntity<>(new StringResponse("User has been modified"), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            LOGGER.error(e.getCause().getMessage());
            return new ResponseEntity<>(new StringResponse(e.getCause().getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/insert-image")
    public ResponseEntity<StringResponse> insertImageUser(@RequestBody ImageResponse imageResponse) {

        try {
            userService.insertImageUser(imageResponse);
            return new ResponseEntity<>(new StringResponse("User has been modified"), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            LOGGER.error(e.getCause().getMessage());
            return new ResponseEntity<>(new StringResponse(e.getCause().getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/save-image-local/{name}/{userId}")
    public ResponseEntity saveImageLocal(@RequestPart("file") MultipartFile file, @PathVariable String name, @PathVariable int userId) {
        try {
            userService.saveImageLocal(file,name,userId);
            return new ResponseEntity<>(new StringResponse("User has been modified"), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            LOGGER.error(e.getCause().getMessage());
            return new ResponseEntity<>(new StringResponse(e.getCause().getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @PostMapping("/history-image/{name}/{userId}")
//    public ResponseEntity saveHistoryImages(@PathVariable String name , @PathVariable int userId){
//        try {
//            userService.saveImageLocal(file,name,userId);
//            return new ResponseEntity<>(new StringResponse("User has been modified"), HttpStatus.ACCEPTED);
//        }catch (Exception e) {
//            LOGGER.error(e.getCause().getMessage());
//            return new ResponseEntity<>(new StringResponse(e.getCause().getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }



    @DeleteMapping ("/delete-image-history/{user_id}")
    public ResponseEntity<Object> ImageHistoryUser(@PathVariable int user_id) {
        try {
            return new ResponseEntity<>(userService.modifyNumberOfImagesHistory(user_id), HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return new ResponseEntity<>(new StringResponse(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete-image-local/{name}")
    public ResponseEntity deleteImageLocal( @PathVariable String name){
        try{
            userService.deleteImageLocal(name);
            return new ResponseEntity<>(new StringResponse("User has been modified"), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            LOGGER.error(e.getCause().getMessage());
            return new ResponseEntity<>(new StringResponse(e.getCause().getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/open-file/{userId}")
    public ResponseEntity<Resource> openFileBrowser(@PathVariable int userId) throws IOException {
        filePath = filePathAbsolute+userId ;

        Process process = Runtime.getRuntime().exec("explorer "+ filePath);
        return null;
    }


}
