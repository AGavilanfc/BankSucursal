package com.optimissa.training.accountservice.repository;

import com.optimissa.training.accountservice.models.Country;
import com.optimissa.training.accountservice.models.Entity;

import java.util.List;

public interface IentityRepository {

    List<Entity> getAll();
}
