package com.vaadin.model;

public class MovieGoerModel {
    private String email;
    private String password;

    public MovieGoerModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public MovieGoerModel() {
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
