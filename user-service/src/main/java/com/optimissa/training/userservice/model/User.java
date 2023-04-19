package com.optimissa.training.userservice.model;

public class User {

    private int id;
    private String name;
    private String lastName1;
    private String lastName2;
    private String email;
    private String phone;
    private Boolean active;

    public User() {
    }

    public User(String name, String lastName1, String lastName2, String email, String phone, Boolean active) {
        this.name = name;
        this.lastName1 = lastName1;
        this.lastName2 = lastName2;
        this.email = email;
        this.phone = phone;
        this.active = active;
    }

    public User(int id, String name, String lastName1, String lastName2, String email, String phone, Boolean active) {
        this.id = id;
        this.name = name;
        this.lastName1 = lastName1;
        this.lastName2 = lastName2;
        this.email = email;
        this.phone = phone;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName1() {
        return lastName1;
    }

    public void setLastName1(String lastName1) {
        this.lastName1 = lastName1;
    }

    public String getLastName2() {
        return lastName2;
    }

    public void setLastName2(String lastName2) {
        this.lastName2 = lastName2;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName1='" + lastName1 + '\'' +
                ", lastName2='" + lastName2 + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", active=" + active +
                '}';
    }
}
