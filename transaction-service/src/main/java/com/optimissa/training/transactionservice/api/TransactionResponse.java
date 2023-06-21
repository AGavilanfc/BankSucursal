package com.optimissa.training.transactionservice.api;

import com.optimissa.training.transactionservice.dtos.Account;

import java.util.Date;

public class TransactionResponse {
    private String date;
    private double amount;

    private String concept;
    private AccountResponse account;
    private FundResponse fund;

    public TransactionResponse() {
    }

    public TransactionResponse(String date, double amount, String concept, AccountResponse account, FundResponse fund) {
        this.date = date;
        this.amount = amount;
        this.concept = concept;
        this.account = account;
        this.fund = fund;
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
        return account;
    }

    public void setAccountResponse(AccountResponse account) {
        this.account = account;
    }

    public FundResponse getFundResponse() {
        return fund;
    }

    public void setFundResponse(FundResponse fund) {
        this.fund = fund;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }
}
