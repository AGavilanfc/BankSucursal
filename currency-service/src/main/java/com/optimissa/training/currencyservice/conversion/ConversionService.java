package com.optimissa.training.currencyservice.conversion;


import com.optimissa.training.currencyservice.dto.ConversionResponse;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;

@Service
public class ConversionService {

    private RestTemplate restTemplate;
    public ConversionResponse convertCurrency(
            String from,
            String to,
            Double amount
    ) {
        String url = "https://api.exchangerate.host/convert?from=" + from + "&to=" + to + "&amount=" + amount;
        restTemplate = new RestTemplate();
        String conversion =  restTemplate.getForObject(url, String.class, from, to, amount);
        ConversionResponse conversionResponse = restTemplate.getForObject(url, ConversionResponse.class, from, to, amount);
        return conversionResponse;
    }

}