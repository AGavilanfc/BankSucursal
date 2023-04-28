package com.optimissa.training.fundservice.api;

public class FundResponse {

    private String name;
    private String refNumber;
    private int currencyId;

    public String getName() { return name;}

    public void setName(String name) { this.name = name;}

    public String getRefNumber() { return refNumber;}

    public void setRefNumber(String refNumber) { this.refNumber = refNumber;}

    public int getCurrencyId() { return currencyId;}

    public void setCurrencyId(int currencyId) { this.currencyId = currencyId;}
}
