package com.optimissa.training.transactionservice.controllers;

import com.optimissa.training.transactionservice.api.TransactionResponse;
import com.optimissa.training.transactionservice.dtos.Transaction;
import com.optimissa.training.transactionservice.services.TransactionsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/transactions")
public class TransactionsController {

    private static final Logger log = LoggerFactory.getLogger(TransactionsController.class);
    RestTemplate restTemplate = new RestTemplate();
    String urlFund = "http://localhost:8095/funds";
    String urlAccount = "http://localhost:8092/accounts/getAccounts";
    String urlCurrency = "http://localhost:8093/currencies";


    @Autowired
    private TransactionsService tranService;

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Object> getTransaction(@PathVariable int id) {
        Long start = System.currentTimeMillis();

        try {
            log.info("obtain one transaction: {}", id);
            TransactionResponse transaction = tranService.getByIdTransaction(id);
            Long end = System.currentTimeMillis();
            log.info("getTransaction, Time: {}", (end - start));
            return ResponseEntity.ok(transaction);

        } catch (Exception e) {
            log.error("getTransaction, Error: {}", ResponseEntity.status(HttpStatus.NOT_FOUND).build());
            return new ResponseEntity<>("Id doesnt exist. " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<Object> getAllTransaction(@RequestParam int limit, @RequestParam int page) {
        Long start = System.currentTimeMillis();

        log.info("obtain all transactions");
        try {
            List<Transaction> transactions = tranService.getAllTransactions(limit, page);
            log.info("obtain all transactions with limit=" + limit + " and page=" + page);
            Long end = System.currentTimeMillis();
            log.info("Time: {}", (end - start));
            return ResponseEntity.ok(transactions);
        } catch (Exception e) {
            log.error("getAllTransaction, Bad request: Error: {}", e.getMessage());
            return ResponseEntity.badRequest().body("Bad request: " + e.getMessage());

        }


    }


    @GetMapping("/get-all/filter")
    public ResponseEntity<Object> getAllTransactionsByAmount(@RequestParam int min, @RequestParam int max) {
        Long start = System.currentTimeMillis();
        if (min > max) throw new IllegalArgumentException("min must be less than max");
        try {
            List<Transaction> transaction = tranService.getAllTransactionsByAmount(min, max);
            Long end = System.currentTimeMillis();
            log.info("Time: {}", (end - start));
            log.info("obtained all by amount min= " + min + " max= " + max);
            return ResponseEntity.ok(transaction);
        } catch (Exception e) {
            log.error("getAllTransactionsByAmount, Error: {}{}", ResponseEntity.internalServerError().build(), e.getMessage(), e.getStackTrace());
            return new ResponseEntity<>("Not created. " + e.getMessage(), HttpStatus.BAD_REQUEST);

        }

    }

    @PostMapping
    public ResponseEntity<Object> createTransaction(@RequestBody Transaction transaction) {
        Long start = System.currentTimeMillis();

        try {
            int transaction2 = tranService.insertNewTransaction(transaction);
            ResponseEntity.ok(transaction2);
            Long end = System.currentTimeMillis();
            log.info("Time: {}", (end - start));

        } catch (Exception e) {
            log.error("createTransaction, Error: {}{}", ResponseEntity.internalServerError().build(), e.getMessage(), e.getStackTrace());
            return new ResponseEntity<>("Not created. " + e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return ResponseEntity.ok().body("Transaction created");
    }


    @GetMapping("/fund/{id}")
    public Object getFund(@PathVariable int id) {
        Long start = System.currentTimeMillis();

        log.info("obtain one fund: {}", id);
        try {
            return restTemplate.getForObject(urlFund + "/id/" + id, Object.class);
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
        }
        Long end = System.currentTimeMillis();
        log.info("Time: {}", (end - start));

        return null;
    }
}