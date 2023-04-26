package com.optimissa.training.transactionservice.api;

public class AccountResponse {


    private int id;
    private Boolean active;
    private String currency_id;

    public AccountResponse() {
    }

    public AccountResponse(int id, Boolean active, String currency_id) {
        this.id = id;
        this.active = active;
        this.currency_id = currency_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getactive() {
        return active;
    }

    public void setactive(Boolean active) {
        this.active = active;
    }

    public String getcurrency_id() {
        return currency_id;
    }

    public void setcurrency_id(String currency_id) {
        this.currency_id = currency_id;
    }
}
