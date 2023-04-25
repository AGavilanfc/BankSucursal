package com.optimissa.training.accountservice.api;

public class AccountResponse {

    private int iban_id;
    private int client_id;
    private int currency_id;

    public AccountResponse() {
    }

    public AccountResponse(int iban_id, int client_id, int currency_id) {
        this.iban_id = iban_id;
        this.client_id = client_id;
        this.currency_id = currency_id;
    }

    public int getIban_id() {
        return iban_id;
    }

    public int getClient_id() {
        return client_id;
    }

    public int getCurrency_id() {
        return currency_id;
    }

    public void setIban_id(int iban_id) {
        this.iban_id = iban_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public void setCurrency_id(int currency_id) {
        this.currency_id = currency_id;
    }
}
