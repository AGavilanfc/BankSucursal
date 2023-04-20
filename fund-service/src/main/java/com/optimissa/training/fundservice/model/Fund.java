package com.optimissa.training.fundservice.model;

public class Fund {

    private int id;
    private String name;
    private String refNumber;
    private int currencyId;
    private boolean active;

    public Fund() {
    }

    public Fund(int id, String name, String refNumber, int currencyId, boolean active) {
        this.id = id;
        this.name = name;
        this.refNumber = refNumber;
        this.currencyId = currencyId;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "FundService{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", refNumber='" + refNumber + '\'' +
                ", currencyId=" + currencyId +
                ", active=" + active +
                '}';
    }
}
