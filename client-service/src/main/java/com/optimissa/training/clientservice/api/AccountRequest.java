package com.optimissa.training.clientservice.api;

public class AccountRequest {

    private int id;

    private int balance;

    private boolean active;

    private int ibanId;

    private int clientId;

    private int currencyId;

    private String iban;



    public AccountRequest(String iban, int balance, int currencyId) {
        this.iban = iban;
        this.balance = balance;
        this.currencyId = currencyId;
    }

    public String getIban() {
        return iban;
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

    public int getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }
}
