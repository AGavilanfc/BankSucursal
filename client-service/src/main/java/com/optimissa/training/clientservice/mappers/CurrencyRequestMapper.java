package com.optimissa.training.clientservice.mappers;

import com.optimissa.training.clientservice.api.CurrencyRequest;
import com.optimissa.training.clientservice.api.CurrencyResponse;

public class CurrencyRequestMapper {

    public static CurrencyResponse mapToCurrencyResponse(CurrencyRequest currency) {
        CurrencyResponse currencyResponse = new CurrencyResponse();
        currencyResponse.setCode(currency.getCode());
        return currencyResponse;
    }
}
