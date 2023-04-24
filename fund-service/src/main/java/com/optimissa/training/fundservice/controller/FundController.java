package com.optimissa.training.fundservice.controller;

import com.optimissa.training.fundservice.model.Fund;
import com.optimissa.training.fundservice.service.FundService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/funds")
public class FundController {

    private final FundService fundService;

    public FundController(FundService fundService) {
        this.fundService = fundService;
    }

    @PostMapping
    public int saveFund(@RequestBody Fund fund){
        return fundService.saveFund(fund);
    }


}
