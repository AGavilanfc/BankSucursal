package com.optimissa.training.countryservice.controller;
import com.optimissa.training.countryservice.models.country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.optimissa.training.countryservice.service.countryService;

import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/countries")
public class countryController {
    @Autowired
    private countryService countryService;
    Logger logger = LoggerFactory.getLogger(countryController.class);

    @GetMapping("/get-all")
    public List getAllCountry() {
        logger.info("estamos entrando en getAllCountry");
        Long start = System.currentTimeMillis();
        List<country> countries = countryService.getAllCountries();
        Long end = System.currentTimeMillis();
        logger.info("getAllCountries, Time: {}", (end - start));
        return countries;
    }

    @GetMapping(value = "/get-by-id/{id}")
    public ResponseEntity<Object> getCountryById(@PathVariable int id) {
        try {
            Long start = System.currentTimeMillis();
            country country = countryService.getCountryById(id);
            Long end = System.currentTimeMillis();
            logger.info("getCurrencyById, Time: {}", (end - start));
            return ResponseEntity.ok(country);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID not found");
        }
    }

}
