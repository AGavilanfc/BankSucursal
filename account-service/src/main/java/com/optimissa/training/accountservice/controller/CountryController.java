package com.optimissa.training.accountservice.controller;
import com.optimissa.training.accountservice.models.Country;
import com.optimissa.training.accountservice.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/country")
public class CountryController {

    @Autowired
    private CountryService countryService;
    @GetMapping("")
    public List<Country> getAllCountries(){

        return countryService.getAll();

    }

}
