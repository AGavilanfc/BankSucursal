package com.optimissa.training.productservice.api;

public class ProductRequest {
    private String name;

    public ProductRequest() {
    }

    public ProductRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ProductRequest{" +
                "name='" + name + '\'' +
                '}';
    }
}
