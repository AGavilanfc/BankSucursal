package com.optimissa.training.currencyservice.conversion;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/convert")
public class ConversionService {

    private RestTemplate restTemplate;

    @GetMapping
    public String convertCurrency(
            @RequestParam("from") String from,
            @RequestParam("to") String to,
            @RequestParam("amount") Double amount
    ) {
        String url = "https://api.exchangerate.host/convert?from=" + from + "&to=" + to + "&amount=" + amount;
        restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class, from, to, amount);
    }

}