package com.optimissa.training.clientservice.api;

public class AccountRequest {

    private String iban;

    private int balance;

    private String currency;

    public AccountRequest(String iban, int balance, String currency) {
        this.iban = iban;
        this.balance = balance;
        this.currency = currency;
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
