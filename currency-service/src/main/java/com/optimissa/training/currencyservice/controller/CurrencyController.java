package com.optimissa.training.currencyservice.controller;
import java.util.*;
import com.optimissa.training.currencyservice.model.Currency;
import com.optimissa.training.currencyservice.repository.CurrencyRepository;
import com.optimissa.training.currencyservice.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping
    public List<Currency> getAllCurrencies() {
        return currencyService.getAllCurrencies();
    }

    @GetMapping(value = "/{id}")
    public Currency getCurrencyById(@PathVariable int id) {
        return currencyService.getCurrencyById(id);
    }

    @PostMapping(value = "/")
    @ResponseStatus(HttpStatus.CREATED)
    public String createCurrency(@RequestBody Currency currency) {
        return currencyService.createCurrency(currency);
    }



    @DeleteMapping("/{id}")
    public int deleteCurrency(@PathVariable int id) {
        return currencyService.deleteById(id);

    }

    

}


