package com.optimissa.training.accountservice.api;

public class CurrencyResponse {

    String name;
    String symbol;
    String code;


    public CurrencyResponse() {
    }
    public CurrencyResponse(String name, String symbolCurrency, String code) {
        this.name = name;
        this.symbol = symbolCurrency;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
