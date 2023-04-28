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



//quitar el dinero de la cuenta (ID, DINERO>0):
    // 1. comrobar si existe la cuenta y si esta activa
    //2. Mirar cuanto dinero hay en la cuena
       // mirar si hay suficiente dinero para restar
    //3. restar de la cuenta el dinero que nos pasan
    // 4. guardar el la bbdd el nuevo valor



    @GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Account> getAllAccount() {
        return accountService.getAllAccount();
    }

    @GetMapping(value = "/get-by-id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getAccount(@PathVariable int id) {
        try{
            Account account = accountService.getAccount(id);
            return account;
        }catch (Exception e){
            return new ResponseEntity<>("can't get an account "+id, HttpStatus.BAD_REQUEST);
        }

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
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>("can't create an account", HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/update/to-account/{id}/add-balance/{balance}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addBalance(@PathVariable int id , @PathVariable double balance){

        if(accountService.updateBalance(id,balance)>0){
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("can't change the balance",HttpStatus.BAD_REQUEST);
    }

//    @PutMapping(value = "/update/to-account/{id}/add-balance/{balance}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity substractBalance(@PathVariable int id , @PathVariable double balance){
//
//        if(accountService.updateBalanceDeduct(id,balance)>0){
//            return new ResponseEntity<>(HttpStatus.ACCEPTED);
//        }
//        return new ResponseEntity<>("can't change the balance",HttpStatus.BAD_REQUEST);
//    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StringResponse> deleteAccount(@PathVariable int id) {

        return ResponseEntity.ok(accountService.deleteAccount(id));
    }


}

