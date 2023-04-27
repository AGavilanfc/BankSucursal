package com.optimissa.training.fundservice.repository;

import com.optimissa.training.fundservice.model.Fund;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface FundRepository {


    int save(Fund fund);

    List<Fund> findAll();
    Fund findById(int id);
    List<Fund> findByName(String name);
    Fund findByRefNumber(String refNumber);
    List<Fund> findByCurrencyId(int currencyId);
    List<Fund> findByActive(boolean active);

    int delete(int id);
    int update(Fund fund, int id);
}
