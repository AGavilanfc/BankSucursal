package com.optimissa.training.transactionservice.dtos;

public class Account {
    private int id;
    private double balance;
    private boolean active;
    private int iban_id;
    private int client_id;
    private int currency_id;
    private String currency;

    public Account() {
    }

    public Account(int id, double balance, boolean active, int iban_id, int client_id, int currency_id) {
        this.id = id;
        this.balance = balance;
        this.active = active;
        this.iban_id = iban_id;
        this.client_id = client_id;
        this.currency_id = currency_id;
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

    public int getCurrency_id() {
        return currency_id;
    }

    public void setCurrency_id(int currency_id) {
        this.currency_id = currency_id;
    }
}

