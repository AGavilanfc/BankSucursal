package com.optimissa.training.userservice.api;

public class ImageResponse {

    private int userId;
    private String name;

    public ImageResponse() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
