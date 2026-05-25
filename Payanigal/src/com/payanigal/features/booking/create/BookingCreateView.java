package com.payanigal.features.booking.create;
import com.payanigal.data.dto.Booking;
import com.payanigal.data.dto.User;
import com.payanigal.util.ConsoleInput;
import com.payanigal.util.ParseHelper;
import java.util.List;
import java.util.Scanner;
public class BookingCreateView {
    private final BookingCreateModel model;
    private final User currentUser;
    private final Scanner scanner;
    public BookingCreateView(User currentUser) {
        this.model= new BookingCreateModel(this);
        this.currentUser = currentUser;
        this.scanner= ConsoleInput.getScanner();
    }
    public void init() {
        System.out.println();
        System.out.println("=== Book a Ticket ===");
        List<String> cities = model.getAllCities();
        if (cities.isEmpty()) { System.out.println("No routes available yet."); return; }
        System.out.println("Available cities: " + String.join(", ", cities));
        System.out.println();
        String origin = promptCity("From (Origin): ");
        String destination = promptDestination(origin);
        String travelDate = promptTravelDate();
        int passengers = promptPassengerCount();
        double fare = model.calculateFare(origin, destination, passengers);
        if (fare == 0) {
            System.out.println("No direct route found for " + origin + " -> " + destination + ".");
            return;
        }
        System.out.println();
        System.out.println("--- Booking Summary ---");
        System.out.println("Route      : " + origin + " -> " + destination);
        System.out.println("Date       : " + travelDate);
        System.out.println("Passengers : " + passengers);
        System.out.println("Total Fare : " + ParseHelper.formatFare(fare));
        System.out.print("Confirm booking? (Y/N): ");
        if (!ParseHelper.isYes(scanner.nextLine())) { System.out.println("Booking cancelled."); return; }
        model.confirmBooking(currentUser, origin, destination, travelDate, passengers);
    }
    private String promptCity(String label) {
        while (true) {
            System.out.print(label);
            String in = scanner.nextLine();
            if (model.validateCity(in) == null) return in.trim();
            System.out.println("  Error: " + model.validateCity(in));
        }
    }
    private String promptDestination(String origin) {
        while (true) {
            System.out.print("To (Destination): ");
            String in = scanner.nextLine();
            String e1 = model.validateCity(in);
            if (e1 != null) { System.out.println("  Error: " + e1); continue; }
            String e2 = model.validateSameCity(origin, in);
            if (e2 != null) { System.out.println("  Error: " + e2); continue; }
            return in.trim();
        }
    }
    private String promptTravelDate() {
        while (true) {
            System.out.print("Travel Date (dd-MM-yyyy): ");
            String in = scanner.nextLine();
            Long d = model.parseTravelDate(in);
            if (d != null) return in.trim();
            System.out.println("  Error: Enter a valid date (today or future).");
        }
    }
    private int promptPassengerCount() {
        while (true) {
            System.out.print("Number of passengers: ");
            String in  = scanner.nextLine();
            String err = model.validatePassengerCount(in);
            if (err == null) return Integer.parseInt(in.trim());
            System.out.println("  Error: " + err);
        }
    }
    public void onBookingSuccess(Booking booking) {
        System.out.println();
        System.out.println("============================================");
        System.out.println("  BOOKING CONFIRMED!");
        System.out.println("============================================");
        System.out.println("  PNR        : " + booking.getPnr());
        System.out.println("  Route      : " + booking.getOrigin() + " -> " + booking.getDestination());
        System.out.println("  Date       : " + booking.getTravelDate());
        System.out.println("  Passengers : " + booking.getPassengerCount());
        System.out.println("  Total Fare : " + ParseHelper.formatFare(booking.getTotalFare()));
        System.out.println("============================================");
        System.out.println("Please carry this PNR for your journey.");
    }
    public void onBookingFailed(String message) { System.out.println("Error: " + message); }
}
