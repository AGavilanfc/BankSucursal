package com.optimissa.training.fundservice.controller;

import com.optimissa.training.fundservice.model.Fund;
import com.optimissa.training.fundservice.service.FundService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funds")
public class FundController {

    private final FundService fundService;

    public FundController(FundService fundService) {
        this.fundService = fundService;
    }

    @PostMapping
    public int saveFund(@RequestBody Fund fund) {
        return fundService.saveFund(fund);
    }

    @GetMapping
    public List<Fund> getAllFunds() {
        return fundService.getAllFunds();
    }

    @GetMapping("/id/{id}")
    public Fund getById(@PathVariable int id) {
        return fundService.getById(id);
    }

    @GetMapping("/name/{name}")
    public List<Fund> getByName(@PathVariable String name) {
        return fundService.getByName(name);
    }

    @GetMapping("/refNumber/{refNumber}")
    public Fund getByRefNumber(@PathVariable String refNumber) {
        return fundService.getByRefNumber(refNumber);
    }

    @GetMapping("/currencyId/{currencyId}")
    public List<Fund> getByCurrencyId(@PathVariable int currencyId) {
        return fundService.getByCurrencyId(currencyId);
    }

    @GetMapping("/active/{active}")
    public List<Fund> getByActive(@PathVariable boolean active) {
        return fundService.getByActive(active);
    }

    @DeleteMapping("/id/{id}")
    public void deleteById(@PathVariable int id) {
        fundService.deleteById(id);
    }

    @PatchMapping("/{id}")
    public int update(@PathVariable int id, @RequestBody Fund fund) {
        return fundService.update(fund, id);
    }

}
