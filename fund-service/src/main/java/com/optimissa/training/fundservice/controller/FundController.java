package com.optimissa.training.fundservice.controller;

import com.optimissa.training.fundservice.api.FundRequest;
import com.optimissa.training.fundservice.model.Fund;
import com.optimissa.training.fundservice.service.FundService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.util.List;

@RestController
@RequestMapping("/funds")
public class FundController {

    Logger logger = LoggerFactory.getLogger(FundController.class);

    RestTemplate restTemplate = new RestTemplate();
    String urlProduct = "http://localhost:8096/products";
    private final FundService fundService;


    public FundController(FundService fundService) {
        this.fundService = fundService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> saveFund(@RequestBody Fund fund) {
        logger.info("Calling saveFund");
        int result = fundService.saveFund(fund);
        if (result >0) {
            return ResponseEntity.ok("Fund created successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to create new Fund");
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Fund>> getAllFunds() {
        logger.info("Calling getAllFunds()");
        List<Fund> funds = fundService.getAllFunds();
        return new ResponseEntity<>(funds, HttpStatus.OK);
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Fund> getById(@PathVariable int id) {
        logger.info("Calling getProductById for id {}", id);
        Fund fund = fundService.getById(id);
        if (fund != null) {
            return new ResponseEntity<>(fund, HttpStatus.OK);
        } else {
            logger.error("Fund with id {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-by-name/{name}")
    public ResponseEntity<List<Fund>> getByName(@PathVariable String name) {
        logger.info("Calling getFundByName for {}", name);
        List<Fund> funds = fundService.getByName(name);
        if (!funds.isEmpty()) {
            return new ResponseEntity<>(funds, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-by-refNumber/{refNumber}")
    public ResponseEntity<Fund> getByRefNumber(@PathVariable String refNumber) {
        logger.info("Calling getFundByRefNumber for {}", refNumber);
        Fund fund = fundService.getByRefNumber(refNumber);
        if (fund != null) {
            return new ResponseEntity<>(fund, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-by-currencyId/{currencyId}")
    public ResponseEntity<List<Fund>> getByCurrencyId(@PathVariable int currencyId) {
        logger.info("Calling getFundByCurrencyId for {}", currencyId);
        List<Fund> funds = fundService.getByCurrencyId(currencyId);
        if (!funds.isEmpty()) {
            return new ResponseEntity<>(funds, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-all/active")
    public ResponseEntity<List<Fund>> getByActive() {
        logger.info("Calling getActives");
        List<Fund> funds = fundService.getByActive();
        if (!funds.isEmpty()) {
            return new ResponseEntity<>(funds, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-all/inactive")
    public ResponseEntity<List<Fund>> getByInactive() {
        logger.info("Calling getInactive");
        List<Fund> funds = fundService.getByInactive();
        if (!funds.isEmpty()) {
            return new ResponseEntity<>(funds, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id) {
        logger.info("Calling deleteFund for id {}", id);
        int result = fundService.deleteById(id);
        if (result > 0) {
            return new ResponseEntity<>("Fund with id " + id + " has been successfully deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Fund with id " + id + " was not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateFund(@PathVariable("id") int id, @RequestBody FundRequest fundRequest) {
        logger.info("Calling updateFund for id {}", id);
        int result = fundService.update(id, fundRequest);
        if (result > 0) {
            return new ResponseEntity<>("Fund with id " + id + " has been successfully updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Fund with id " + id + " was not found", HttpStatus.NOT_FOUND);
        }
    }





}
