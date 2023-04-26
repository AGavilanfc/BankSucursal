package com.optimissa.training.currencyservice.exchange;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@RestController
@RequestMapping("/codes")
public class exchangeService {
    @GetMapping
    public List getUserClients() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("https://api.apilayer.com/exchangerates_data/symbols?apikey=9ddOkbDKYoY30bCsVyoBbTkUMgigDlk8", List.class);
    }
}
