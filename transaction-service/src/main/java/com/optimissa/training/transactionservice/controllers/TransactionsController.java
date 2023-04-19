package com.optimissa.training.transactionservice.controllers;

import com.optimissa.training.transactionservice.dtos.Transactions;
import com.optimissa.training.transactionservice.services.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionsController {

    @Autowired
    private TransactionsService tranService;



    @GetMapping("/{id}")
    public String getTransaction(@PathVariable String id) {

        return tranService.getSaludo(id);
    }
    @GetMapping("/all")
    public List<Transactions> getAllTransaction() {

        return tranService.getAllTransactions();
    }



    @PostMapping
    public String createTransaction(@RequestBody String transaction) {

        return "este es mi id: " + transaction;
    }

}