package com.optimissa.training.accountservice.controller;
import com.optimissa.training.accountservice.api.AccountRequest;
import com.optimissa.training.accountservice.api.AccountResponse;
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


    @GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Account> getAllAccount() {
        return accountService.getAllAccount();
    }

    @GetMapping(value = "/get-by-id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAccount(@PathVariable int id) {

            AccountResponse account = accountService.getAccount(id);
            return ResponseEntity.ok(account);


    }


    @GetMapping(value = "/get-by-client/{clientId}")
    public List<Account> getAccountByClient(@PathVariable int clientId){
        return accountService.getAccountByClient(clientId);
    }

    @PostMapping(value = "/create", produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity newAccount(@RequestBody AccountRequest accountRequest) {
        //accountrequest para recoger los datos recibidos por la request
        if(accountService.createAccount(accountRequest)){
            return new ResponseEntity<>("New account create correctly", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("can't create an account", HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/update/to-account/{id}/add-balance/{balance}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addBalance(@PathVariable int id , @PathVariable double balance){

        if(accountService.updateBalance(id,balance)==0){
            return new ResponseEntity<>("Balance has been changed successfully",HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("can't change the balance",HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/update/to-account/{id}/substract-balance/{balance}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity substractBalance(@PathVariable int id , @PathVariable double balance){

        if(accountService.updateBalanceDeduct(id,balance)==0){
            return new ResponseEntity<>("Balance has been changed successfully",HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("can't change the balance",HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StringResponse> deleteAccount(@PathVariable int id) {

        return ResponseEntity.ok(accountService.deleteAccount(id));
    }
    
}

