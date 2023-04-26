package com.optimissa.training.transactionservice.api;

public class FundResponse {
    private String name;
    private String refNumber;
    private Boolean active;


    public FundResponse() {
    }

    public FundResponse(String name, String refNumber, Boolean active) {
        this.name = name;
        this.refNumber = refNumber;
        this.active = active;
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

    public Boolean getactive() {
        return active;
    }

    public void setactive(Boolean active) {
        this.active = active;
    }
}
