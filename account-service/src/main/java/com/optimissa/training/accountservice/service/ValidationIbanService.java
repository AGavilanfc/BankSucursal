package com.optimissa.training.accountservice.service;

import com.optimissa.training.accountservice.models.Country;
import com.optimissa.training.accountservice.models.Entity;
import com.optimissa.training.accountservice.models.Iban;
import com.optimissa.training.accountservice.repository.CountryRepository;
import com.optimissa.training.accountservice.repository.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidationIbanService {

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    EntityRepository entityRepository;

    public boolean validate(int ibanCountry, int ibanEntity) {

        boolean resultCountry = countryRepository.getAll()
                .stream()
                .map(Country::getId)
                .anyMatch(integer -> integer == ibanCountry);

        boolean resultEntity = entityRepository.getAll()
                .stream()
                .map(Entity::getId)
                .anyMatch(integer -> integer == ibanEntity);

        return resultCountry && resultEntity;
    }



}
