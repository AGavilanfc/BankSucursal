package com.optimissa.training.currencyservice.conversion;

public class ConversionResponse {
    private boolean success;
    private double result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }
}
