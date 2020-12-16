package com.vaadin.model;

public class TicketSeatModel {
    private int number;
    private boolean taken;
    private Integer ownerId;
    private Integer screeningId;

    public TicketSeatModel() {
    }

    public TicketSeatModel(int number, boolean taken, Integer ownerId, Integer screeningId) {
        this.number = number;
        this.taken = taken;
        this.ownerId = ownerId;
        this.screeningId = screeningId;
    }

    public int getNumber() {
        return number;
    }

    public boolean isTaken() {
        return taken;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public Integer getScreeningId() { return screeningId; }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public void setScreeningId(Integer screeningId) {
        this.screeningId = screeningId;
    }
}
