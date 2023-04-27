package com.optimissa.training.currencyservice.dto;

public class Info {
    private double rate;

    public Info() {
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Info{" +
                "rate=" + rate +
                '}';
    }
}
