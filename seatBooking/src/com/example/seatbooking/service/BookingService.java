package com.example.seatbooking.service;

import com.example.seatbooking.dao.SeatRepository;
import com.example.seatbooking.model.*;

import java.util.*;
import java.util.stream.Collectors;

public class BookingService {
    private final SeatRepository seatRepo;
    private final Map<String, Booking> bookings = new HashMap<>();

    public BookingService(SeatRepository seatRepo) {
        this.seatRepo = seatRepo;
    }

    /** FR1: Book a seat (first available) */
    public Booking bookSeat(String customer) {
        Seat seat = seatRepo.findFirstFree()
                .orElseThrow(() -> new IllegalStateException("All seats are full"));
        seat.setBooked(true);
        Booking booking = new Booking(customer, seat.getNumber());
        bookings.put(booking.getId(), booking);
        return booking;
    }

    /** FR2: Cancel an existing booking */
    public boolean cancelBooking(String bookingId) {
        Booking booking = bookings.remove(bookingId);
        if (booking == null)
            return false;
        seatRepo.getSeat(booking.getSeatNumber()).ifPresent(s -> s.setBooked(false));
        return true;
    }

    /** FR3: View current seat map */
    public List<Seat> viewSeatMap() {
        return seatRepo.getAll();
    }

    /** FR4: List every booking */
    public List<Booking> listBookings() {
        return new ArrayList<>(bookings.values());
    }

    /** FR5: Find bookings by customer name (case-insensitive) */
    public List<Booking> findByCustomer(String customer) {
        return bookings.values().stream()
                .filter(b -> b.getCustomer().equalsIgnoreCase(customer))
                .collect(Collectors.toList());
    }
}