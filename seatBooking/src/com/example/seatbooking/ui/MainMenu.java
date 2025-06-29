package com.example.seatbooking.ui;

import com.example.seatbooking.controller.BookingController;
import com.example.seatbooking.dao.SeatRepository;
import com.example.seatbooking.model.*;
import com.example.seatbooking.service.BookingService;

import java.util.List;
import java.util.Scanner;

public class MainMenu {
    private static final int CAPACITY = 10; // change as needed
    private final BookingController controller;
    private final Scanner sc = new Scanner(System.in);

    public MainMenu() {
        SeatRepository repo = new SeatRepository(CAPACITY);
        controller = new BookingController(new BookingService(repo));
    }

    private void loop() {
        while (true) {
            System.out.println("\n--- Seat Booking Menu ---");
            System.out.println("1) Book a seat");
            System.out.println("2) Cancel booking");
            System.out.println("3) View seat map");
            System.out.println("4) List all bookings");
            System.out.println("5) Find booking by customer");
            System.out.println("0) Exit");
            System.out.print("Choose: ");

            switch (sc.nextLine()) {
                case "1" -> bookSeat();
                case "2" -> cancel();
                case "3" -> showMap();
                case "4" -> listBookings();
                case "5" -> search();
                case "0" -> {
                    System.out.println("Bye!");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void bookSeat() {
        System.out.print("Customer name: ");
        String name = sc.nextLine().trim();
        try {
            Booking booking = controller.book(name);
            System.out.println("Booked successfully: " + booking);
        } catch (IllegalStateException ex) {
            System.out.println("Booking failed: " + ex.getMessage());
        }
    }

    private void cancel() {
        System.out.print("Booking ID: ");
        if (controller.cancel(sc.nextLine().trim()))
            System.out.println("Cancelled.");
        else
            System.out.println("Booking not found.");
    }

    private void showMap() {
        List<Seat> map = controller.seatMap();
        map.forEach(System.out::println);
    }

    private void listBookings() {
        controller.allBookings().forEach(System.out::println);
    }

    private void search() {
        System.out.print("Customer name: ");
        controller.searchCustomer(sc.nextLine().trim())
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        new MainMenu().loop();
    }
}