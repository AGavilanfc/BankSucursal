package com.optimissa.training.transactionservice.controllers;

import com.optimissa.training.transactionservice.dtos.Transactions;
import com.optimissa.training.transactionservice.services.TransactionsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionsController {

    private static final Logger log = LoggerFactory.getLogger(TransactionsController.class);


    @Autowired
    private TransactionsService tranService;

    @GetMapping("/{id}")
    public Transactions getTransaction(@PathVariable int id) {
        log.info("getTransaction: " + id);

        try{
           return tranService.getByIdTransaction(id);
        }catch (Exception e){
            log.error("Error: " + e.getMessage());

        }
        return null;


    }
    @GetMapping("")
    public List<Transactions> getAllTransaction() {
        log.info("getAllTransaction");
        try{
            return tranService.getAllTransactions();
        }catch (Exception e){
            log.error("Error: " + e.getMessage());

        }

        return null;
    }

    @PostMapping
    public Object createTransaction(@RequestBody Transactions transaction) {
        Long start=System.currentTimeMillis();
        log.info("createTransaction: " + transaction);
        try{
            return tranService.insertNewTransaction(transaction);
        }catch (Exception e){
            log.error("Error: " + e.getMessage());

        }
        Long end=System.currentTimeMillis();
        log.info("Time: " + (end-start));

        return null;
    }



    

}