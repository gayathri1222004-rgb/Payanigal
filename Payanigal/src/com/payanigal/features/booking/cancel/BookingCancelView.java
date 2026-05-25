package com.payanigal.features.booking.cancel;
import com.payanigal.data.dto.Booking;
import com.payanigal.data.dto.User;
import com.payanigal.util.ConsoleInput;
import com.payanigal.util.ParseHelper;
import java.util.List;
import java.util.Scanner;
public class BookingCancelView {
    private final BookingCancelModel model;
    private final User currentUser;
    private final Scanner scanner;
    public BookingCancelView(User currentUser) {
        this.model= new BookingCancelModel(this);
        this.currentUser= currentUser;
        this.scanner= ConsoleInput.getScanner();
    }
    public void init() {
        System.out.println();
        System.out.println("=== Cancel Booking ===");
        List<Booking> active = model.getActiveBookingsForUser(currentUser.getId());
        if (active.isEmpty()) { System.out.println("You have no active bookings to cancel."); return; }
        printBookings(active);
        Booking chosen = pickBooking(active);
        if (chosen == null) return;
        System.out.println();
        System.out.println("You are about to cancel:");
        System.out.println("  PNR        : " + chosen.getPnr());
        System.out.println("  Route      : " + chosen.getOrigin() + " -> " + chosen.getDestination());
        System.out.println("  Date       : " + chosen.getTravelDate());
        System.out.println("  Passengers : " + chosen.getPassengerCount());
        System.out.println("  Fare       : " + ParseHelper.formatFare(chosen.getTotalFare()));
        System.out.print("Are you sure? (Y/N): ");
        if (!ParseHelper.isYes(scanner.nextLine())) { System.out.println("Cancellation aborted."); return; }
        String reason = promptReason();
        model.cancel(chosen, reason);
    }
    private void printBookings(List<Booking> list) {
        System.out.printf("%-4s %-14s %-24s %-12s %-6s %s%n", "#", "PNR", "Route", "Date", "Pax", "Fare");
        System.out.println("-".repeat(78));
        for (int i = 0; i < list.size(); i++) {
            Booking b = list.get(i);
            System.out.printf("%-4d %-14s %-24s %-12s %-6d %s%n",
                    i + 1, b.getPnr(),
                    ParseHelper.trunc(b.getOrigin() + " -> " + b.getDestination(), 23),
                    b.getTravelDate(), b.getPassengerCount(),
                    ParseHelper.formatFare(b.getTotalFare()));
        }
    }
    private Booking pickBooking(List<Booking> list) {
        while (true) {
            System.out.print("Select booking number (or 0 to go back): ");
            Integer n = ParseHelper.parsePositiveInt(scanner.nextLine());
            if (n == null)
            { System.out.println("  Error: Invalid choice."); continue; }
            if (n == 0)
                return null;
            if (n < 1 || n > list.size()) { System.out.println("  Error: Enter between 1 and " + list.size()); continue; }
            return list.get(n - 1);
        }
    }
    private String promptReason() {
        while (true) {
            System.out.print("Reason for cancellation: ");
            String in = scanner.nextLine();
            String err = model.validateReason(in);
            if (err == null) return in.trim();
            System.out.println("  Error: " + err);
        }
    }
    void onCancelSuccess(Booking booking) {
        System.out.println();
        System.out.println("Booking " + booking.getPnr() + " has been CANCELLED successfully.");
        System.out.println("Refund of " + ParseHelper.formatFare(booking.getTotalFare())
                + " will be processed in 5-7 business days.");
    }
    void onCancelFailed(String message) { System.out.println("Error: " + message); }
}
