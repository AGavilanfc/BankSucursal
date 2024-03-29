package com.optimissa.training.fundservice.service;

import com.optimissa.training.fundservice.api.FundRequest;
import com.optimissa.training.fundservice.controller.FundController;
import com.optimissa.training.fundservice.mapper.FundRequestMapper;
import com.optimissa.training.fundservice.model.Fund;
import com.optimissa.training.fundservice.repository.FundRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FundService {

    RestTemplate restTemplate = new RestTemplate();
    String urlProduct = "http://localhost:8096/products";

    private final Logger logger = LoggerFactory.getLogger(FundController.class);


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
    public List<Fund> getByActive() { return fundRepository.findByActive();}
    public List<Fund> getByInactive() { return fundRepository.findByInactive();}
    public int deleteById(int id) { return fundRepository.delete(id); }
    public int update(int id, FundRequest fund) {
        Fund modifiedFund = FundRequestMapper.mapToFund(fund);
        return fundRepository.update(id, modifiedFund); }
}
