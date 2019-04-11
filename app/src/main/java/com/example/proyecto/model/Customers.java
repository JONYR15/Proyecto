package com.example.proyecto.model;

public class Customers {

    private Integer id;
    private String name;
    private String lastName;
    private String numberPhone;
    private String email;
    private String user;
    private String pass;

    public Customers() {
    }

    public Customers(Integer id, String name, String lastName, String numberPhone, String email, String user, String pass) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.numberPhone = numberPhone;
        this.email = email;
        this.user = user;
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "Customers{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", lastName=" + lastName +
                ", numberPhone=" + numberPhone +
                ", email" + email +
                ", user" + user +
                ", pass" + pass +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
