package com.optimissa.training.transactionservice.dtos;

public class Transactions {

    public Transactions(int id, String date, String transactioNumber, double amount, int account_Id, int fund_Id) {
        this.id = id;
        this.date = date;
        this.transactioNumber = transactioNumber;
        this.amount = amount;
        this.account_Id = account_Id;
        this.fund_Id = fund_Id;
    }
    private int id;
    private String date;
    private String transactioNumber;
    private double amount;
    private int account_Id;
    private int fund_Id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTransactioNumber() {
        return transactioNumber;
    }

    public void setTransactioNumber(String transactioNumber) {
        this.transactioNumber = transactioNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getAccount_Id() {
        return account_Id;
    }

    public void setAccount_Id(int account_Id) {
        this.account_Id = account_Id;
    }

    public int getFund_Id() {
        return fund_Id;
    }

    public void setFund_Id(int fund_Id) {
        this.fund_Id = fund_Id;
    }
}
