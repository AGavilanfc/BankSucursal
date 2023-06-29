package com.optimissa.training.entityservice.repository;

import com.optimissa.training.entityservice.models.entity;

import java.util.List;

public interface IentityRepository {
    public List<entity> getAll();
    public entity findById(int id);
}
