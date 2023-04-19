package com.optimissa.training.transactionservice.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionsController {

    @GetMapping("/{id}")
    public String getTransaction(@PathVariable int id) {

        return "este es mi id: " + id;
    }

    @PostMapping
    public String createTransaction(@RequestBody String transaction) {

        return "este es mi id: " + transaction;
    }

}