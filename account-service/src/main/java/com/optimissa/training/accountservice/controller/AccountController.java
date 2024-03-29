package com.optimissa.training.accountservice.controller;

import com.optimissa.training.accountservice.api.AccountRequest;
import com.optimissa.training.accountservice.api.AccountResponse;
import com.optimissa.training.accountservice.mapper.AccountRequestMapper;
import com.optimissa.training.accountservice.models.Account;
import com.optimissa.training.accountservice.models.StringResponse;
import com.optimissa.training.accountservice.service.AccountService;
import com.optimissa.training.accountservice.service.ValidationIbanService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/accounts")
public class AccountController {

    private final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(AccountController.class.getName());

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

            try {
                AccountResponse account = accountService.getAccount(id);
                return ResponseEntity.ok(account);
            }catch(Exception e){
//                LOGGER.error(e.getCause().getMessage());
                return null;
            }
    }
    @GetMapping(value = "/get-by-client/{clientId}")
    public List<Account> getAccountByClient(@PathVariable int clientId){
        try {
            return accountService.getAccountByClient(clientId);
        }catch (Exception e){
            LOGGER.error(e.getCause().getMessage());
            return null;
        }
    }

    @PostMapping(value = "/create", produces = "application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity newAccount(@RequestBody AccountRequest accountRequest) {

        try{
            if(accountService.createAccount(accountRequest)){
                return new ResponseEntity<>("New account create correctly", HttpStatus.CREATED);
            }
            return new ResponseEntity<>("can't create an account", HttpStatus.BAD_REQUEST);
        }catch(Exception e ){
            LOGGER.error(e.getCause().getMessage());
            return new ResponseEntity<>(new StringResponse(e.getCause().getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/update/to-account/{id}/add-balance/{balance}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addBalance(@PathVariable int id , @PathVariable double balance){

        try{
            if(accountService.updateBalance(id,balance)==0){
                return new ResponseEntity<>("Balance has been changed successfully",HttpStatus.ACCEPTED);
            }
            return new ResponseEntity<>("can't change the balance",HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            LOGGER.error(e.getCause().getMessage());
            return new ResponseEntity<>(new StringResponse(e.getCause().getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/id/{id}")
    public ResponseEntity<StringResponse> modifyUser(@RequestBody Account account, @PathVariable int id) {
                try {
                    if (accountService.modifyAccount(account, id) == 1) {
                        return new ResponseEntity<>(new StringResponse("Account has been modified"), HttpStatus.ACCEPTED);
                    } else {
                        return new ResponseEntity<>(new StringResponse("Account id does not exist"), HttpStatus.NOT_FOUND);
                    }
                } catch (Exception e) {
                    LOGGER.error(e.getCause().getMessage());
                    return new ResponseEntity<>(new StringResponse(e.getCause().getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
                }
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

        try{
            return ResponseEntity.ok(accountService.deleteAccount(id));
        }catch (Exception e){
            LOGGER.error(e.getCause().getMessage());
            return new ResponseEntity<>(new StringResponse(e.getCause().getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/update/id/{id}/rol/{rol}")
    public ResponseEntity<Object> updateAccountRol(@PathVariable int id, @PathVariable String rol) {
        try {
            return new ResponseEntity<>(accountService.updateAccountRol(id, rol), HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error(e.getCause().getMessage());
            return new ResponseEntity<>(new StringResponse(e.getCause().getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/update/id/{id}/status/{active}")
    public ResponseEntity<Object> updateAccountStatus(@PathVariable int id, @PathVariable int active) {
        try {
            return new ResponseEntity<>(accountService.updateAccountStatus(id, active), HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error(e.getCause().getMessage());
            return new ResponseEntity<>(new StringResponse(e.getCause().getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

