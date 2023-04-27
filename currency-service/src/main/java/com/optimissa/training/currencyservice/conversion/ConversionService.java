package com.optimissa.training.currencyservice.conversion;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/convert")
public class ConversionService {

    private RestTemplate restTemplate;

    @GetMapping
    public String getLatestRates() {
        String url = "https://api.frankfurter.app/latest?amount=&from=&to=";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        return response;
    }

}