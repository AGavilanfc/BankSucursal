package com.optimissa.training.transactionservice.api;

import com.optimissa.training.transactionservice.dtos.Account;

import java.util.Date;

public class TransactionResponse {
    private String date;
    private double amount;
    private AccountResponse accountResponse;
    private FundResponse fundResponse;

    public TransactionResponse() {
    }

    public TransactionResponse(String date, double amount, AccountResponse accountResponse, FundResponse fundResponse) {
        this.date = date;
        this.amount = amount;
        this.accountResponse = accountResponse;
        this.fundResponse = fundResponse;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public AccountResponse getAccountResponse() {
        return accountResponse;
    }

    public void setAccountResponse(AccountResponse accountResponse) {
        this.accountResponse = accountResponse;
    }

    public FundResponse getFundResponse() {
        return fundResponse;
    }

    public void setFundResponse(FundResponse fundResponse) {
        this.fundResponse = fundResponse;
    }
}
