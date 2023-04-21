package com.optimissa.training.accountservice.controller;

import com.optimissa.training.accountservice.models.Account;
import com.optimissa.training.accountservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping(value = "/getAccount/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Account getAccount(@PathVariable int id){
        Account account = accountService.getAccount(id);
        return account;
    }


    @PostMapping(value = "/newAccount", produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Account newAccount(@RequestBody Account account){
        Account accountPost = accountService.createAccount(account);
        return accountPost;
    }

    @PutMapping(value = "update/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Account updateAccount(@PathVariable int id, @RequestBody Account account) {
        Account accountPut = accountService.updateAccount(id,account);
        return accountPut;
    }

    @DeleteMapping(value = "/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccount(@PathVariable int id) {
        accountService.deleteAccount(id);
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

