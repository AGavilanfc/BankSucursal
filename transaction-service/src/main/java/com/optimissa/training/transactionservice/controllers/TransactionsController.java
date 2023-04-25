package com.optimissa.training.transactionservice.controllers;

import com.optimissa.training.transactionservice.dtos.Transactions;
import com.optimissa.training.transactionservice.services.TransactionsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionsController{

    private static final Logger log = LoggerFactory.getLogger(TransactionsController.class);
    RestTemplate restTemplate = new RestTemplate();
    String urlFund = "http://localhost:8095/funds";
    String urlAccount = "http://localhost:8096/api/some-endpoint";


    @Autowired
    private TransactionsService tranService;

    @GetMapping("/{id}")
    public ResponseEntity<Transactions> getTransaction(@PathVariable int id) {

        try {
            if(ResponseEntity.ok(tranService.getByIdTransaction(id)) != null){
                log.info("obtain one transaction: {}", id);
                return ResponseEntity.ok(tranService.getByIdTransaction(id));
            }
        } catch (Exception e) {
            log.error("Error:{} {}", ResponseEntity.internalServerError().build(), e.getMessage());

        }
        return ResponseEntity.internalServerError().build();


    }

    @GetMapping("")
    public List<Transactions> getAllTransaction() {
        log.info("obtain all transactions");
        try {
            return tranService.getAllTransactions();
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());

        }

        return null;
    }

    @PostMapping
    public ResponseEntity<Integer> createTransaction(@RequestBody Transactions transaction) {
        Long start = System.currentTimeMillis();
        try {
            if(ResponseEntity.ok(tranService.insertNewTransaction(transaction)) != null
                    && ResponseEntity.ok(tranService.insertNewTransaction(transaction)) != null){
                log.info("create transaction: {}", transaction);
                return ResponseEntity.ok(tranService.insertNewTransaction(transaction));
            }
            ResponseEntity.ok(tranService.insertNewTransaction(transaction));
        } catch (Exception e) {
            log.error("Error: {}", ResponseEntity.internalServerError().build(), e.getMessage());

        }
        Long end = System.currentTimeMillis();
        log.info("Time: {}", (end - start));

        return ResponseEntity.internalServerError().build();
    }


    @GetMapping("/fund/{id}")
    public Object getFund(@PathVariable int id) {
        log.info("obtain one fund: {}", id);

        try {
            return restTemplate.getForObject(urlFund + "/" + id, Object.class);
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());



        }
        return null;
    }
}