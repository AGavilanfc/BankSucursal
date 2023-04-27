package com.optimissa.training.userservice.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserResponse {

    private String name;
    private String email;
    private String phone;
    private List<Object> clients;

    public UserResponse() {
    }

    public UserResponse(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.clients = new ArrayList<>();
    }

    public UserResponse(String name, String email, String phone, List<Object> clients) {
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

    public List<Object> getClients() {
        return clients;
    }

    public void setClients(List<Object> clients) {
        this.clients = clients;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", clients=" + clients +
                '}';
    }
}
