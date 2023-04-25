package com.optimissa.training.accountservice.models;

public class Country {
    private int id;
    private int code;
    private String name;

    public Country() {
    }

    public Country(int id, int code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
