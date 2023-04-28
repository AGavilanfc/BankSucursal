package com.optimissa.training.clientservice.api;

public class AccountRequest {

    private int id;

    private int balance;

    private boolean active;

    private int iban_id;

    private int client_id;

    private int currency_id;

    private String iban;


    public String getIban() {
        return iban;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getIban_id() {
        return iban_id;
    }

    public void setIban_id(int iban_id) {
        this.iban_id = iban_id;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getCurrency_id() {
        return currency_id;
    }

    public void setCurrency_id(int currency_id) {
        this.currency_id = currency_id;
    }
}
