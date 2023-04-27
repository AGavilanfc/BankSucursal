package com.optimissa.training.currencyservice.dto;

public class ConversionResponse {

    private Info info;
    private double result;

    public ConversionResponse() {
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ConversionResponse{" +
                "info=" + info +
                ", result=" + result +
                '}';
    }
}
