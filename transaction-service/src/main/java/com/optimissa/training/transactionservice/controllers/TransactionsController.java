package com.optimissa.training.transactionservice.controllers;

import com.optimissa.training.transactionservice.dtos.Transactions;
import com.optimissa.training.transactionservice.repository.TransactionRespository;
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
    public Transactions getTransaction(@PathVariable int id) {

        return tranService.getByIdTransaction(id);
    }
    @GetMapping("")
    public List<Transactions> getAllTransaction() {

        return tranService.getAllTransactions();
    }

    @PostMapping
    public int createTransaction(@RequestBody Transactions transaction) {

        return tranService.insertNewTransaction(transaction);
    }

}