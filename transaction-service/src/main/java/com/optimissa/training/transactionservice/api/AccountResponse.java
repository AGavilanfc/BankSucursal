package com.optimissa.training.transactionservice.api;

public class AccountResponse {


    private int id;

    private double balance;
    private Boolean active;
    private int currency_id;

    private CurrencyResponse currencyResponse;

    public AccountResponse() {
    }

    public AccountResponse(int id,double balance, Boolean active, int currency_id, CurrencyResponse currencyResponse) {
        this.id = id;
        this.balance = balance;
        this.active = active;
        this.currency_id = currency_id;
        this.currencyResponse = currencyResponse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Boolean getactive() {
        return active;
    }

    public void setactive(Boolean active) {
        this.active = active;
    }

    public int getcurrency_id() {
        return currency_id;
    }

    public void setcurrency_id(int currency_id) {
        this.currency_id = currency_id;
    }

    public CurrencyResponse getCurrencyResponse() {
        return currencyResponse;
    }

    public void setCurrencyResponse(CurrencyResponse currencyResponse) {
        this.currencyResponse = currencyResponse;
    }
}
