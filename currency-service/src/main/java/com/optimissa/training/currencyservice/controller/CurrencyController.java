package com.optimissa.training.currencyservice.controller;
import java.util.*;
import com.optimissa.training.currencyservice.model.Currency;
import com.optimissa.training.currencyservice.repository.CurrencyRepository;
import com.optimissa.training.currencyservice.service.CurrencyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {

    Logger logger = LoggerFactory.getLogger(CurrencyController.class);
    @Autowired
    private CurrencyService currencyService;

    @GetMapping
    public List<Currency> getAllCurrencies() {
        logger.info("estamos entrando en getAllCurrencies");
        return currencyService.getAllCurrencies();
    }

    @GetMapping(value = "/{id}")
    public Currency getCurrencyById(@PathVariable int id) {
        logger.info("estamos entrando en getById {}",id);
       return currencyService.getCurrencyById(id);
    }

    @PostMapping(value = "/")
    @ResponseStatus(HttpStatus.CREATED)
    public String createCurrency(@RequestBody Currency currency) {
        logger.info("estamos creando una moneda");
       return currencyService.createCurrency(currency);
    }



    @DeleteMapping("/{id}")
    public int deleteCurrency(@PathVariable int id) {
        logger.info("estamos borrando una moneda");
        return currencyService.deleteById(id);

    }


    @PatchMapping("/{id}")
    public String updateCurrency(@PathVariable int id, @RequestBody Currency currency) {
        logger.info("estamos editando una moneda");
        boolean updated = currencyService.updateCurrency(id, currency);
        if (updated) {
            return "Currency updated successfully!";
        } else {
            return "Failed to update currency";
        }

    }


}


