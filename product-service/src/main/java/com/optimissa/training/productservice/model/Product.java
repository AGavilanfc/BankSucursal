package com.optimissa.training.productservice.model;

import java.util.Date;

public class Product {
    private int id;
    private String name;
    private Boolean active;
    private Date activeDate;
    private Date inactiveDate;

    public Product() {
    }

    public Product(int id, String name, Boolean active, Date activeDate, Date inactiveDate) {
        this.id = id;
        this.name = name;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getActiveDate() {
        return activeDate;
    }

    public void setActiveDate(Date activeDate) {
        this.activeDate = activeDate;
    }

    public Date getInactiveDate() {
        return inactiveDate;
    }

    public void setInactiveDate(Date inactiveDate) {
        this.inactiveDate = inactiveDate;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", active=" + active +
                ", activeDate=" + activeDate +
                ", inactiveDate=" + inactiveDate +
                '}';
    }
}
