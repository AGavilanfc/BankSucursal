package com.optimissa.training.fundservice.service;

import com.optimissa.training.fundservice.model.Fund;
import com.optimissa.training.fundservice.repository.FundRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FundService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FundService.class);


    private final FundRepository fundRepository;
    @Autowired
    private FundService(FundRepository fundRepository) {
        this.fundRepository = fundRepository;
    }


    public int saveFund(Fund fund) { return fundRepository.save(fund);}
    public List<Fund> getAllFunds(){ return fundRepository.findAll();}
    public Fund getById(int id) { return fundRepository.findById(id);}
    public List<Fund> getByName(String name) { return fundRepository.findByName(name);}
    public Fund getByRefNumber(String refNumber) { return fundRepository.findByRefNumber(refNumber);}
    public List<Fund> getByCurrencyId(int currencyId) { return fundRepository.findByCurrencyId(currencyId);}
    public List<Fund> getByActive(boolean active) { return fundRepository.findByActive(active);}
    public void deleteById(int id) {
        fundRepository.delete(id);
    }
    public int update(int id, Fund fund) { return fundRepository.update(fund,id); }


}