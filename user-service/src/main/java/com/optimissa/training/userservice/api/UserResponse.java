package com.optimissa.training.userservice.api;

import java.util.Arrays;

public class UserResponse {

    private String name;
    private String email;
    private String phone;
    private Object[] clients;

    public UserResponse() {
    }

    public UserResponse(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.clients = new Object[]{};
    }

    public UserResponse(String name, String email, String phone, Object[] clients) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.clients = clients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Object[] getClients() {
        return clients;
    }

    public void setClients(Object[] clients) {
        this.clients = clients;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", clients=" + Arrays.toString(clients) +
                '}';
    }
}
