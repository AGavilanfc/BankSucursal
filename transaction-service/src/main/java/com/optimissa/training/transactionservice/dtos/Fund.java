package com.optimissa.training.transactionservice.dtos;

import java.util.Date;

public class Fund {
    private int id;
    private String name;
    private String refNumber;
    private int currencyId;
    private boolean active;
    private Date activeDate;

    public Fund() {
    }

    public Fund(int id, String name, String refNumber, int currencyId, boolean active,Date activeDate) {
        this.id = id;
        this.name = name;
        this.refNumber = refNumber;
        this.currencyId = currencyId;
        this.active = active;
        this.activeDate = activeDate;
    }

    public Date getActiveDate() {
        return activeDate;
    }

    public void setActiveDate(Date activeDate) {
        this.activeDate = activeDate;
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
}



