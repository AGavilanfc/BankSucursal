package com.optimissa.training.transactionservice.dtos;

public class Transaction {

    private int id;
    private String date;

    private String transaction_Number;
    private double amount;
    private int account_Id;
    private int fund_Id;

    private String concept;

    public Transaction() {
    }

    public Transaction(int id, String date, String transaction_Number, double amount, int account_Id, int fund_Id) {
        this.id = id;
        this.date = date;
        this.transaction_Number = transaction_Number;
        this.amount = amount;
        this.account_Id = account_Id;
        this.fund_Id = fund_Id;
    }

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

    public String getTransaction_Number() {
        return transaction_Number;
    }

    public void setTransaction_Number(String transaction_Number) {
        this.transaction_Number = transaction_Number;
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

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    @Override
    public String toString() {
        return "Transactions{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", transaction_Number='" + transaction_Number + '\'' +
                ", amount=" + amount +
                ", account_Id=" + account_Id +
                ", fund_Id=" + fund_Id +
                '}';
    }
}
