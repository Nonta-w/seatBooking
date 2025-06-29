package com.example.seatbooking.model;

public class Seat {
    private final int number;
    private boolean booked;

    public Seat(int number) {
        this.number = number;
        this.booked = false;
    }

    public int getNumber() {
        return number;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    @Override
    public String toString() {
        return "Seat " + number + (booked ? " [BOOKED]" : " [FREE]");
    }
}