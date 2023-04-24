package com.optimissa.training.accountservice.models;

import java.io.Serializable;

public class Iban implements Serializable {

private int id ;
private int country_id;
private int country_control;
private int entity_id;
private int branch;
private int account_control;
private int account_number;

    public Iban() {
    }

    public Iban(int id, int country_id, int country_control, int entity_id, int branch, int account_control, int account_number) {
        this.id = id;
        this.country_id = country_id;
        this.country_control = country_control;
        this.entity_id = entity_id;
        this.branch = branch;
        this.account_control = account_control;
        this.account_number = account_number;
    }

    public int getId() {
        return id;
    }

    public int getCountry_id() {
        return country_id;
    }

    public int getCountry_control() {
        return country_control;
    }

    public int getEntity_id() {
        return entity_id;
    }

    public int getBranch() {
        return branch;
    }

    public int getAccount_control() {
        return account_control;
    }

    public int getAccount_number() {
        return account_number;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }

    public void setCountry_control(int country_control) {
        this.country_control = country_control;
    }

    public void setEntity_id(int entity_id) {
        this.entity_id = entity_id;
    }

    public void setBranch(int branch) {
        this.branch = branch;
    }

    public void setAccount_control(int account_control) {
        this.account_control = account_control;
    }

    public void setAccount_number(int account_number) {
        this.account_number = account_number;
    }
}
