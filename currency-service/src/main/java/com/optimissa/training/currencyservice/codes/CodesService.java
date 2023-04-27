package com.optimissa.training.currencyservice.codes;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/codes")
public class CodesService {
    @GetMapping
    public Map<String, String> getCurrencies() {
        RestTemplate restTemplate = new RestTemplate();
        CodesResponse response = restTemplate.getForObject(
                "https://api.apilayer.com/exchangerates_data/symbols?apikey=9ddOkbDKYoY30bCsVyoBbTkUMgigDlk8",
                CodesResponse.class);
        return response.getSymbols();
    }



}

