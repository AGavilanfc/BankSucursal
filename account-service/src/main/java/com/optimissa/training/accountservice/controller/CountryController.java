package com.optimissa.training.accountservice.controller;
import com.optimissa.training.accountservice.models.Country;
import com.optimissa.training.accountservice.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
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
