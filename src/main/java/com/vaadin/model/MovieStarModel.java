package com.vaadin.model;

public class MovieStarModel {
    private String firstName;
    private String lastName;

    public MovieStarModel() {
    }

    public MovieStarModel(String firstName, String secondName) {
        this.firstName = firstName;
        this.lastName = secondName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String secondName) {
        this.lastName = secondName;
    }

}
