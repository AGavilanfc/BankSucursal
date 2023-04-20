package com.optimissa.training.accountservice.Controller;

import com.optimissa.training.accountservice.Models.Account;
import com.optimissa.training.accountservice.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    //@GetMapping("/{id}")
    @GetMapping(value = "/getAccount/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Account getAccount(@PathVariable int id){
        Account account = accountService.getAccount(id);
        return account;
    }


    @PostMapping(value = "/newAccount", produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String newAccount(@RequestBody Account account){

        return "OK";
    }

//    @PostMapping("/")
//    public ResponseEntity<User> createUser(@RequestBody User user) {
//        User newUser = userService.createUser(user);
//        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
//    }

}

//    @GetMapping(value = "/getAccount/{id}", produces = "application/json")
//    public Account getAccount(@PathVariable int id){
//
//        return new Account(1000, 1234);
//    }

