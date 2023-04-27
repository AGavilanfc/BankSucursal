package com.optimissa.training.fundservice.mapper;

import com.optimissa.training.fundservice.api.FundRequest;
import com.optimissa.training.fundservice.model.Fund;

public class FundRequestMapper {

    public static Fund mapToFund(FundRequest fundRequest){
        Fund fund = new Fund();
        fund.setName(fundRequest.getName());
        fund.setRefNumber(fundRequest.getRefNumber());
        fund.setCurrencyId(fundRequest.getCurrencyId());
        return fund;
    }
}
