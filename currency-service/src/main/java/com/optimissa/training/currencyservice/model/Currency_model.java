package com.optimissa.training.currencyservice.model;

public class Currency_model {
    private int id;
    private String name;
    private String symbol;
    private String code;


    public Currency_model(String name, String symbol, String code) {
        this.name = name;
        this.symbol = symbol;
        this.code = code;
    }

    public Currency_model(int id, String name, String symbol, String code) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.code = code;
    }

    public Currency_model() {
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
