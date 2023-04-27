package com.optimissa.training.accountservice.service;

import com.optimissa.training.accountservice.models.Country;
import com.optimissa.training.accountservice.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;
    public List<Country> getAll(){

        return countryRepository.getAll();
    }
}
