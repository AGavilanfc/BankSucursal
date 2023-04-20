package com.optimissa.training.transactionservice.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="TRANSACTION")
public class TransactionsEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="DATE", nullable = false)
    private LocalDateTime date;

    @Column(name="TRANSACTION_NUMBER", nullable = false, unique = true)
    private String transaction_number;

    @Column(name="AMOUNT", nullable = false)
    private double amount;

    @ManyToOne
    @Column(name="ACCOUNT_ID", nullable = false)
    private int account_Id;

    @ManyToOne
    @Column(name="FUND_ID", nullable = false)
    private int fund_Id;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getTransaction_number() {
        return transaction_number;
    }

    public void setTransaction_number(String transaction_number) {
        this.transaction_number = transaction_number;
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
