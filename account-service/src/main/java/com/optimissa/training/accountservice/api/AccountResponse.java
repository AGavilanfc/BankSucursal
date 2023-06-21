package com.optimissa.training.accountservice.api;

public class AccountResponse {

    private int id;
    private double balance;
    private boolean active;
    private int iban_id;
    private int client_id;
    private CurrencyResponse currencyResponse;
    private String iban;

    private String rol;


    public AccountResponse(int id, double balance, int ibanId, int clientId, CurrencyResponse byIdAccount, String iban , String rol) {

    }




    public AccountResponse(int id, double balance, boolean active, int iban_id, int client_id, CurrencyResponse currencyResponse, String iban , String rol) {
        this.id = id;
        this.balance = balance;
        this.active = active;
        this.iban_id = iban_id;
        this.client_id = client_id;
        this.currencyResponse = currencyResponse;
        this.iban = iban;
        this.rol=rol;
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

    public CurrencyResponse getCurrencyResponse() {
        return currencyResponse;
    }

    public void setCurrencyResponse(CurrencyResponse currencyResponse) {
        this.currencyResponse = currencyResponse;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }
    public String getRol() {
        return rol;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }
    @Override
    public String toString() {
        return "AccountResponse{" +
                "id=" + id +
                ", balance=" + balance +
                ", active=" + active +
                ", iban_id=" + iban_id +
                ", client_id=" + client_id +
                ", currencyResponse=" + currencyResponse +
                ", iban='" + iban + '\'' +
                '}';
    }
}
