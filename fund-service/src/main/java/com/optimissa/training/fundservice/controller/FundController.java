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

@CrossOrigin
@RestController
@RequestMapping("/funds")
public class FundController {

    Logger logger = LoggerFactory.getLogger(FundController.class);

    RestTemplate restTemplate = new RestTemplate();
    String urlProduct = "http://localhost:8096/products";
    private FundService fundService;


    public FundController(FundService fundService) {
        this.fundService = fundService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> saveFund(@RequestBody Fund fund) {
        long millis = System.currentTimeMillis();
        int result = fundService.saveFund(fund);
        if (result > 0) {
            logger.info("Fund saved successfully in " + (System.currentTimeMillis() - millis) + " millis");
            return ResponseEntity.ok("Fund created successfully");
        } else {
            logger.error("Failed to create new Fund in " + (System.currentTimeMillis() - millis) + " millis");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to create new Fund");
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Fund>> getAllFunds() {
        long startMillis = System.currentTimeMillis();
        logger.info("Calling getAllFunds() at " + startMillis + " millis");
        List<Fund> funds = fundService.getAllFunds();
        logger.info("Retrieved {} funds in " + (System.currentTimeMillis() - startMillis) + " millis", funds.size());
        return new ResponseEntity<>(funds, HttpStatus.OK);
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Fund> getById(@PathVariable int id) {
        long startMillis = System.currentTimeMillis();
        logger.info("Calling getById for id {} at " + startMillis + " millis", id);
        Fund fund = fundService.getById(id);
        if (fund != null) {
            logger.info("Retrieved Fund with id {} in " + (System.currentTimeMillis() - startMillis) + " millis", id);
            return new ResponseEntity<>(fund, HttpStatus.OK);
        } else {
            logger.error("Fund with id {} not found in " + (System.currentTimeMillis() - startMillis) + " millis", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-by-name/{name}")
    public ResponseEntity<List<Fund>> getByName(@PathVariable String name) {
        long startMillis = System.currentTimeMillis();
        logger.info("Calling getByName for {} at " + startMillis + " millis", name);
        List<Fund> funds = fundService.getByName(name);
        if (!funds.isEmpty()) {
            logger.info("Retrieved {} funds for name {} in " + (System.currentTimeMillis() - startMillis) + " millis", funds.size(), name);
            return new ResponseEntity<>(funds, HttpStatus.OK);
        } else {
            logger.error("Fund with name {} not found in " + (System.currentTimeMillis() - startMillis) + " millis", name);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-by-refNumber/{refNumber}")
    public ResponseEntity<Fund> getByRefNumber(@PathVariable String refNumber) {
        long startMillis = System.currentTimeMillis();
        logger.info("Calling getByRefNumber for {} at " + startMillis + " millis", refNumber);
        Fund fund = fundService.getByRefNumber(refNumber);
        if (fund != null) {
            logger.info("Retrieved Fund with refNumber {} in " + (System.currentTimeMillis() - startMillis) + " millis", refNumber);
            return new ResponseEntity<>(fund, HttpStatus.OK);
        } else {
            logger.error("Fund with refNumber {} not found in " + (System.currentTimeMillis() - startMillis) + " millis", refNumber);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-by-currencyId/{currencyId}")
    public ResponseEntity<List<Fund>> getByCurrencyId(@PathVariable int currencyId) {
        long startMillis = System.currentTimeMillis();
        logger.info("Calling getByCurrencyId for {} at " + startMillis + " millis", currencyId);
        List<Fund> funds = fundService.getByCurrencyId(currencyId);
        if (!funds.isEmpty()) {
            logger.info("Retrieved {} funds with currencyId {} in " + (System.currentTimeMillis() - startMillis) + " millis", funds.size(), currencyId);
            return new ResponseEntity<>(funds, HttpStatus.OK);
        } else {
            logger.error("No funds found with currencyId {} in " + (System.currentTimeMillis() - startMillis) + " millis", currencyId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-all/active")
    public ResponseEntity<List<Fund>> getByActive() {
        long startMillis = System.currentTimeMillis();
        logger.info("Calling getByActive at " + startMillis + " millis");
        List<Fund> funds = fundService.getByActive();
        if (!funds.isEmpty()) {
            logger.info("Retrieved {} active funds in " + (System.currentTimeMillis() - startMillis) + " millis", funds.size());
            return new ResponseEntity<>(funds, HttpStatus.OK);
        } else {
            logger.error("No active funds found in " + (System.currentTimeMillis() - startMillis) + " millis");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/get-all/inactive")
    public ResponseEntity<List<Fund>> getByInactive() {
        long startMillis = System.currentTimeMillis();
        logger.info("Calling getByInactive at " + startMillis + " millis");
        List<Fund> funds = fundService.getByInactive();
        if (!funds.isEmpty()) {
            logger.info("Retrieved {} inactive funds in " + (System.currentTimeMillis() - startMillis) + " millis", funds.size());
            return new ResponseEntity<>(funds, HttpStatus.OK);
        } else {
            logger.error("No inactive funds found in " + (System.currentTimeMillis() - startMillis) + " millis");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id) {
        long startMillis = System.currentTimeMillis();
        logger.info("Calling deleteFund for id {} at {}", id, startMillis);
        int result = fundService.deleteById(id);
        long endMillis = System.currentTimeMillis();

        if (result == 1) {
            logger.info("Fund with id {} has been successfully deleted in {} ms", id, endMillis-startMillis);
            return new ResponseEntity<>("Fund with id " + id + " has been successfully deleted at " + (endMillis-startMillis), HttpStatus.OK);
        } else {
            logger.info("Fund with id {} has not been deleted in {} ms", id, endMillis-startMillis);
            return new ResponseEntity<>("Fund with id " + id + " was not found at " + (endMillis-startMillis), HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateFund(@PathVariable("id") int id, @RequestBody FundRequest fundRequest) {
        long startMillis = System.currentTimeMillis();
        logger.info("[{}] Calling updateFund for id {}", startMillis, id);
        int result = fundService.update(id, fundRequest);
        long endMillis = System.currentTimeMillis();

        if (result == 1) {
            logger.info("Fund with id {} has been successfully updated in {} ms", id, endMillis-startMillis);
            return new ResponseEntity<>("Fund with id " + id + " has been successfully updated", HttpStatus.OK);
        } else {
            logger.error("Fund with id {} has not been updated in {} ms", id, endMillis-startMillis);
            return new ResponseEntity<>("Fund with id " + id + " was not found", HttpStatus.NOT_FOUND);
        }
    }






}
