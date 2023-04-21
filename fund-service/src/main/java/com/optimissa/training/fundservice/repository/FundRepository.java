package com.optimissa.training.fundservice.repository;

import com.optimissa.training.fundservice.model.Fund;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FundRepository {
    int save(Fund fund);
    List<Fund> findAll();
}
