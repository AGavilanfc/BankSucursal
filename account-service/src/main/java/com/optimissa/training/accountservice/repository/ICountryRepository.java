package com.optimissa.training.accountservice.repository;

import com.optimissa.training.accountservice.models.Country;

import java.util.List;

public interface ICountryRepository {

    List<Country> getAll();
}
