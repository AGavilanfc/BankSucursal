package com.optimissa.training.currencyservice.controller;
import java.util.*;

import com.optimissa.training.currencyservice.conversion.ConversionService;
import com.optimissa.training.currencyservice.dto.ConversionResponse;
import com.optimissa.training.currencyservice.model.Currency;
import com.optimissa.training.currencyservice.service.CurrencyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import com.optimissa.training.currencyservice.exception.MyException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {
    @Autowired
    private ConversionService conversionService;
    Logger logger = LoggerFactory.getLogger(CurrencyController.class);
    @Autowired
    private CurrencyService currencyService;
    @GetMapping("/convert")
    public ResponseEntity<Object> convertCurrency(
            @RequestParam("from") int from,
            @RequestParam("to") int to,
            @RequestParam("amount") Double amount
    ) {
        Currency currencyFrom = null, currencyTo = null;

        try {
            currencyFrom = currencyService.getCurrencyById(from);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("incorrect Id FROM ");
        }

        try {
            currencyTo = currencyService.getCurrencyById(to);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("incorrect Id TO");
        }

        if (currencyFrom == null) {
            return ResponseEntity.badRequest().body("incorrect Id FROM ");
        }

        if (currencyTo == null) {
            return ResponseEntity.badRequest().body("incorrect Id TO");
        }

        return ResponseEntity.ok(conversionService.convertCurrency(currencyFrom.getCode(), currencyTo.getCode(), amount));
    }


    @GetMapping("/get-all")
    public List<Currency> getAllCurrencies() {
        logger.info("estamos entrando en getAllCurrencies");
        return currencyService.getAllCurrencies();
    }

    @GetMapping(value = "/get-by-id/{id}")
    public ResponseEntity<Object> getCurrencyById(@PathVariable int id) {
        try {
            Currency currency = currencyService.getCurrencyById(id);
            return ResponseEntity.ok(currency);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID not found");
        }
    }



    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public String createCurrency(@RequestBody Currency currency) {
        logger.info("estamos creando una moneda");
       return currencyService.createCurrency(currency);
    }



    @DeleteMapping("/delete/{id}")
    public String deleteCurrency(@PathVariable int id) {
        logger.info("estamos borrando una moneda");
        return currencyService.deleteById(id);

    }


    @PatchMapping("/update/{id}")
    public String updateCurrency(@PathVariable int id, @RequestBody Currency currency) {
        logger.info("estamos editando una moneda");
        boolean updated = currencyService.updateCurrency(id, currency);
        if (updated) {
            return "Currency updated successfully!";
        } else {
            return "Failed to update currency";
        }

    }


    @GetMapping(value = "/get-by-name/{name}")
    public ResponseEntity<?> getCurrencyByName(@PathVariable String name) {
        logger.info("estamos entrando en get-by-name {}", name);
        Currency currency = currencyService.getByName(name);
        if (currency != null) {
            return ResponseEntity.ok(currency);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Currency name not found");
        }
    }

    @GetMapping(value = "/get-by-code/{code}")
    public ResponseEntity<?> getCurrencyByCode(@PathVariable String code) {
        logger.info("estamos entrando en get-by-code {}", code);
        try {
            Currency currency = currencyService.getByCode(code);
            return ResponseEntity.ok(currency);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Currency not found");
        }
    }



}


