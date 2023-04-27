package com.optimissa.training.fundservice.controller;

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
    public List<Fund> getAllFunds() { return fundService.getAllFunds(); }

    @GetMapping("/get-by-id/{id}")
    public Fund getById(@PathVariable int id) {
        return fundService.getById(id);
    }

    @GetMapping("/get-by-name/{name}")
    public List<Fund> getByName(@PathVariable String name) {
        return fundService.getByName(name);
    }

    @GetMapping("/get-by-refNumber/{refNumber}")
    public Fund getByRefNumber(@PathVariable String refNumber) {
        return fundService.getByRefNumber(refNumber);
    }

    @GetMapping("/get-by-currencyId/{currencyId}")
    public List<Fund> getByCurrencyId(@PathVariable int currencyId) {
        return fundService.getByCurrencyId(currencyId);
    }

    @GetMapping("/get-all/active")
    public List<Fund> getByActive() { return fundService.getByActive(); }

    @GetMapping("/get-all/inactive")
    public List<Fund> getByInactive() { return fundService.getByInactive(); }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable int id) {
        fundService.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateFund(@PathVariable("id") int id, @RequestBody Fund fund) {
        logger.info("Calling updateFund for id {}", id);
        int result = fundService.update(id, fund);
        if (result > 0) {
            return new ResponseEntity<>("Fund with id " + id + " has been successfully updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Fund with id " + id + " was not found", HttpStatus.NOT_FOUND);
        }
    }





}
