package com.optimissa.training.currencyservice.conversion;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
@RestController
@RequestMapping("/convert")
public class ConversionService {
    @GetMapping
    public double convertCurrency(
            @RequestParam("from") String fromCurrency,
            @RequestParam("to") String toCurrency,
            @RequestParam("amount") double amount) {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("https://xecdapi.xe.com/v1/convert_from.csv/?from=%s&to=%s&amount=%f&apikey=tbque794kacpj5bvemtauds227", fromCurrency, toCurrency, amount);
        ConversionResponse response = restTemplate.getForObject(url, ConversionResponse.class);
        return response.getResult();
    }
}


