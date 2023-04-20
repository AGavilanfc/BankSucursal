package com.optimissa.training.currencyservice.controller;
import java.util.*;
import com.optimissa.training.currencyservice.model.Currency;
import com.optimissa.training.currencyservice.repository.CurrencyRepository;
import com.optimissa.training.currencyservice.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/currencies")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping
    public List<Currency> getAllCurrencies() {
        return currencyService.getAllCurrencies();
    }


}


