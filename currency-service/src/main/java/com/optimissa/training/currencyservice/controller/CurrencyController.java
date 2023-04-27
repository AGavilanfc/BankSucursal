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
    public Object convertCurrency(
            @RequestParam("from") int from,
            @RequestParam("to") int to,
            @RequestParam("amount") Double amount
    ) {
        Currency currencyFrom, currencyTo;
       try {
           currencyFrom = currencyService.getCurrencyById(from);
       } catch(Exception e) {
           return "Id FROM incorrecto";
       }
        try {
            currencyTo = currencyService.getCurrencyById(to);
        } catch(Exception e) {
            return "Id TO incorrecto";
        }

        return conversionService.convertCurrency(currencyFrom.getCode(), currencyTo.getCode(), amount);

    }


    //convert (currencyIDorigenFROM, currencyIDestinoTO, cantidad>0)
    // 1. validar las currency que nos han pasado (BBDD)
    // 2. ejecutar la conversion contra el API externo



    @GetMapping("/get-all")
    public List<Currency> getAllCurrencies() {
        logger.info("estamos entrando en getAllCurrencies");
        return currencyService.getAllCurrencies();
    }

    @GetMapping(value = "/get-by-id/{id}")
    public Currency getCurrencyById(@PathVariable int id) {
        logger.info("estamos entrando en get-by-id {}",id);
        Currency currency = currencyService.getCurrencyById(id);

        return currency;
    }


    @PostMapping(value = "/get-all")
    @ResponseStatus(HttpStatus.CREATED)
    public String createCurrency(@RequestBody Currency currency) {
        logger.info("estamos creando una moneda");
       return currencyService.createCurrency(currency);
    }



    @DeleteMapping("/get-by-id/{id}")
    public String deleteCurrency(@PathVariable int id) {
        logger.info("estamos borrando una moneda");
        return currencyService.deleteById(id);

    }


    @PatchMapping("/get-by-id/{id}")
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


