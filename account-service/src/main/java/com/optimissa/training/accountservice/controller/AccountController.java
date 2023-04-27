package com.optimissa.training.accountservice.controller;
import com.optimissa.training.accountservice.api.AccountRequest;
import com.optimissa.training.accountservice.mapper.AccountRequestMapper;
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
    AccountRequestMapper accountRequestMapper;

    @Autowired
    AccountService accountService;

//get accounts by client

//poner el dinero en la cuenta (ID, DINERO>0):
    // 1. comrobar si existe la cuenta y si esta activa
    //2. Mirar cuanto dinero hay en la cuena
    //3. sumar lo que nos pasan con el dinero existente
    // 4. guardar el la bbdd el nuevo valor
//quitar el dinero de la cuenta (ID, DINERO>0):
    // 1. comrobar si existe la cuenta y si esta activa
    //2. Mirar cuanto dinero hay en la cuena
       // mirar si hay suficiente dinero para restar
    //3. restar de la cuenta el dinero que nos pasan
    // 4. guardar el la bbdd el nuevo valor



    @GetMapping(value = "/getAccounts", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Account> getAllAccount() {
        return accountService.getAllAccount();
    }


    @GetMapping(value = "/getAccount/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Account getAccount(@PathVariable int id) {
        Account account = accountService.getAccount(id);
        return account;
    }


    @PostMapping(value = "/newAccount", produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void newAccount(@RequestBody AccountRequest accountRequest) {
        //accountrequest para recoger los datos recibidos por la request
        accountService.createAccount(accountRequest);
    }


    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public int updateAccount(@PathVariable int id, @RequestBody Account account) {
        return accountService.updateAccount(id, account);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StringResponse> deleteAccount(@PathVariable int id) {

        return ResponseEntity.ok(accountService.deleteAccount(id));
    }

//    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<StringResponse> deleteAccount(@PathVariable int id) {
//        //objeto responsestring
//        return ResponseEntity.ok(accountService.deleteAccount(id));
//    }
}

