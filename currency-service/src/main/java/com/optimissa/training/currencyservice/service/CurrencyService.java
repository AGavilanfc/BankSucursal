package com.optimissa.training.currencyservice.service;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.optimissa.training.currencyservice.model.Currency;
import com.optimissa.training.currencyservice.repository.CurrencyRepository;

@Service
public class CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    public List<Currency> getAllCurrencies() {
        return currencyRepository.getAll();
    }

    public Currency getCurrencyById(int id) {
        Optional<Currency> currency = Optional.ofNullable(currencyRepository.findById(id));
        if (currency.isPresent()) {
            return currency.get();
        } else {
            return null;
        }

        }

public String createCurrency(Currency currency) {
    int createdCurrency = currencyRepository.createCurrency(currency);
    if (createdCurrency == 1) {
        return "Currency created successfully!";
    } else {
        return "Failed to create currency";
    }
}


public int deleteById(int id) {
    Currency currency = currencyRepository.findById(id);
    if(currency == null) {
        String errorMessage = "Currency with id " + id + " not found";
    }
    return currencyRepository.delete(id);

}
}

