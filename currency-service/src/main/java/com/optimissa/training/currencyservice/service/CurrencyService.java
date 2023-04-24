package com.optimissa.training.currencyservice.service;
import java.util.*;

import com.optimissa.training.currencyservice.controller.CurrencyController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.optimissa.training.currencyservice.model.Currency;
import com.optimissa.training.currencyservice.repository.CurrencyRepository;

@Service
public class CurrencyService {

    Logger logger = LoggerFactory.getLogger(CurrencyService.class);
    @Autowired
    private CurrencyRepository currencyRepository;

    public List<Currency> getAllCurrencies() {
        try {
            logger.info("Getting all the currencies");
            return currencyRepository.getAll();
        } catch (Exception e) {
            logger.error("An error occurred while getting all currencies: {}", e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public Currency getCurrencyById(int id) {
        try {
            Optional<Currency> currency = Optional.ofNullable(currencyRepository.findById(id));
            if (currency.isPresent()) {
                logger.info("Searching a currency by ID");
                return currency.get();
            } else {
                logger.warn("Currency with id {} not found", id);
                return null;
            }
        } catch (Exception e) {
            logger.error("An error occurred while getting currency with id {}: {}", id, e.getMessage(), e);
            return null;
        }

        }

public String createCurrency(Currency currency) {
    try {
        int createdCurrency = currencyRepository.createCurrency(currency);
        if (createdCurrency == 1) {
            logger.info("Currency created successfully!");
            return "Currency created successfully!";
        } else {
            logger.warn("Failed to create currency: {}", currency);
            return "Failed to create currency";
        }
    } catch (Exception e) {
        logger.error("An error occurred while creating currency: {}", e.getMessage(), e);
        return "Failed to create currency";
    }
}


public int deleteById(int id) {
    try {
        Currency currency = currencyRepository.findById(id);
        if (currency == null) {
            logger.warn("Currency with id {} not found", id);
            return 0;
        } else {
            logger.info("Currency deleted");
            return currencyRepository.delete(id);
        }
    } catch (Exception e) {
        logger.error("An error occurred while deleting currency with id {}: {}", id, e.getMessage(), e);
        return 0;
    }

}


public boolean updateCurrency(int id, Currency currency) {
    try {
        Currency existingCurrency = currencyRepository.findById(id);
        if (existingCurrency == null) {
            logger.warn("Currency with id {} not found", id);
            return false;
        }

        existingCurrency.setName(currency.getName());
        existingCurrency.setSymbol(currency.getSymbol());
        existingCurrency.setCode(currency.getCode());
        int updated = currencyRepository.update(existingCurrency);
        logger.info("Currency updated successfully!");
        return updated == 1;
    } catch (Exception e) {
        logger.error("An error occurred while updating currency with id {}: {}", id, e.getMessage(), e);
        return false;
    }
}





}

