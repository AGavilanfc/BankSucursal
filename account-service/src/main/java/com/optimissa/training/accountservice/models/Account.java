package com.optimissa.training.accountservice.models;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.annotation.Generated;
import java.io.Serializable;

 @Table(name= "account")
public class Account implements Serializable {


    private int id;
    private double balance;
    private boolean active;
    private int iban_id;
    private int client_id;
    private int currency_id;
    private String iban;

    private String currency;

     private String rol;

//Constructor

    public Account() {
    }

    public int getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public int getIban_id() {
        return iban_id;
    }

    public int getClient_id() {
        return client_id;
    }

    public int getCurrency_id() {
        return currency_id;
    }

    public boolean isActive() {
        return active;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setIban_id(int iban_id) {
        this.iban_id = iban_id;
    }
    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }
    public void setCurrency_id(int currency_id) {
        this.currency_id = currency_id;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
     public String getIban() {
         return iban;
     }
     public void setIban(String iban) {
         this.iban = iban;
     }
     public String getCurrency() {
         return currency;
     }
     public void setCurrency(String currency) {
         this.currency = currency;
     }
     public String getRol() {
         return rol;
     }

     public void setRol(String rol) {
         this.rol = rol;
     }
 }