package com.vaadin.model;

public class AuditoriumModel {
    private int capacity;

    public AuditoriumModel() {
    }

    public AuditoriumModel(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
