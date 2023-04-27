package com.optimissa.training.fundservice.model;

import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;


@Table("FUND")
public class Fund {

    private int id;
    private String name;
    private String refNumber;
    private int currencyId;
    private boolean active;
    private Date activeDate;
    private Date inactiveDate;

    public Fund() {
    }

    public Fund(int id, String name, String refNumber, int currencyId, boolean active, Date activeDate, Date inactiveDate) {
        this.id = id;
        this.name = name;
        this.refNumber = refNumber;
        this.currencyId = currencyId;
        this.active = active;
        this.activeDate = activeDate;
        this.inactiveDate = inactiveDate;
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

    public Date getActiveDate() { return activeDate; }

    public void setActiveDate(Date activeDate) { this.activeDate = activeDate; }

    public Date getInactiveDate() { return inactiveDate; }

    public void setInactiveDate(Date inactiveDate) { this.inactiveDate = inactiveDate; }


    @Override
    public String toString() {
        return "Fund{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", refNumber='" + refNumber + '\'' +
                ", currencyId=" + currencyId +
                ", active=" + active +
                ", activeDate=" + activeDate +
                ", inactiveDate=" + inactiveDate +
                '}';
    }
}
