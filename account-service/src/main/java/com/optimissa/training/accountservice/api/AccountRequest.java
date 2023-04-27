package com.optimissa.training.accountservice.api;

public class AccountRequest {
    private int ibanCountry;

    private int ibanEntity;

    private int clientId;
    private int currencyId;

    public AccountRequest() {
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public  int getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }

    @Override
    public String toString() {
        return "AccountRequest{" +
                ", clientId=" + clientId +
                ", currencyId=" + currencyId +
                '}';
    }

    public int getIbanCountry() {
        return ibanCountry;
    }

    public int getIbanEntity() {
        return ibanEntity;
    }

    public void setIbanEntity(int ibanEntity) {
        this.ibanEntity = ibanEntity;
    }

    public void setIbanCountry(int ibanCountry) {
        this.ibanCountry = ibanCountry;
    }
}
