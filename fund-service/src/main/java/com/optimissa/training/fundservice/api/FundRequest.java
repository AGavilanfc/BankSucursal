package com.optimissa.training.fundservice.api;

public class FundRequest {

    private String name;
    private String refNumber;
    private int currencyId;

    public FundRequest() {
    }

    public FundRequest(String name, String refNumber, int currencyId) {
        this.name = name;
        this.refNumber = refNumber;
        this.currencyId = currencyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRefNumber() {
        return refNumber;
    }

    public void setRefNumber(String refNumber) {
        this.refNumber = refNumber;
    }

    public int getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }

    @Override
    public String toString() {
        return "FundRequest{" +
                "name='" + name + '\'' +
                ", refNumber='" + refNumber + '\'' +
                ", currencyId=" + currencyId +
                '}';
    }
}
