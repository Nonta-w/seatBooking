package com.example.seatbooking.dao;

import com.example.seatbooking.model.Seat;
import java.util.*;

public class SeatRepository {
    private final List<Seat> seats;

    public SeatRepository(int capacity) {
        seats = new ArrayList<>(capacity);
        for (int i = 1; i <= capacity; i++)
            seats.add(new Seat(i));
    }

    public Optional<Seat> findFirstFree() {
        return seats.stream().filter(s -> !s.isBooked()).findFirst();
    }

    public Optional<Seat> getSeat(int number) {
        return seats.stream().filter(s -> s.getNumber() == number).findFirst();
    }

    public List<Seat> getAll() {
        return Collections.unmodifiableList(seats);
    }
}