package com.example.seatbooking.controller;

import com.example.seatbooking.model.*;
import com.example.seatbooking.service.BookingService;

import java.util.List;

public class BookingController {
    private final BookingService service;

    public BookingController(BookingService service) {
        this.service = service;
    }

    public Booking book(String customer) { // delegates to service
        return service.bookSeat(customer);
    }

    public boolean cancel(String bookingId) {
        return service.cancelBooking(bookingId);
    }

    public List<Seat> seatMap() {
        return service.viewSeatMap();
    }

    public List<Booking> allBookings() {
        return service.listBookings();
    }

    public List<Booking> searchCustomer(String name) {
        return service.findByCustomer(name);
    }
}