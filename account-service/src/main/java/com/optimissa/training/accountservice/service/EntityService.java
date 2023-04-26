package com.optimissa.training.accountservice.service;

import com.optimissa.training.accountservice.models.Country;
import com.optimissa.training.accountservice.models.Entity;
import com.optimissa.training.accountservice.repository.CountryRepository;
import com.optimissa.training.accountservice.repository.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

 @Service
public class EntityService {

    @Autowired
    private EntityRepository entityRepository;
    public List<Entity> getAll(){

        return entityRepository.getAll();
    }
}
