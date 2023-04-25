package com.optimissa.training.accountservice.controller;
import com.optimissa.training.accountservice.models.Account;
import com.optimissa.training.accountservice.models.StringResponse;
import com.optimissa.training.accountservice.service.AccountService;
import com.optimissa.training.accountservice.service.ValidationIbanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/accounts")
public class AccountController {

    Logger LOGGER = Logger.getLogger(AccountController.class.getName());

    @Autowired
    ValidationIbanService validationIbanService;

    @Autowired
    AccountService accountService;

    //get all accounts
    @GetMapping(value = "/getAccounts", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Account> getAllAccount(){
        return accountService.getAllAccount();
    }


    @GetMapping(value = "/getAccount/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Account getAccount(@PathVariable int id){
        Account account = accountService.getAccount(id);
        return account;
    }


    @PostMapping(value = "/newAccount", produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public int newAccount(@RequestBody Account account ){
        return accountService.createAccount(account);
//        if(validationIbanService.validate(account.getIban_id())){
//            Account accountPost = accountService.createAccount(account);
//        }else{
//            LOGGER.severe("El iban no es válido");
//        }
    }

    @PutMapping(value = "/update/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public int updateAccount(@PathVariable int id, @RequestBody Account account) {
       return  accountService.updateAccount(id,account);
    }

    @DeleteMapping(value = "/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StringResponse> deleteAccount(@PathVariable int id) {
        //objeto responsestring
        return ResponseEntity.ok(accountService.deleteAccount(id));
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

