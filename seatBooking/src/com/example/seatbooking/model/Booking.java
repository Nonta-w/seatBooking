package com.example.seatbooking.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Booking {
    private final String id; // UUID string
    private final String customer; // customer name
    private final int seatNumber;
    private final LocalDateTime time; // time of booking

    public Booking(String customer, int seatNumber) {
        this.id = UUID.randomUUID().toString();
        this.customer = customer;
        this.seatNumber = seatNumber;
        this.time = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public String getCustomer() {
        return customer;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public LocalDateTime getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "[" + id.substring(0, 8) + "] "
                + customer + " → seat " + seatNumber
                + " at " + time;
    }
}