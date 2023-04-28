package com.optimissa.training.transactionservice.api;

public class AccountRequest {


    private int id;

    private double balance;


    public AccountRequest() {
    }

    public AccountRequest(int id, double balance, Boolean active, int currency_id) {
        this.id = id;
        this.balance = balance;
    }

    public AccountRequest(int idAccount, double balance) {
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

}
