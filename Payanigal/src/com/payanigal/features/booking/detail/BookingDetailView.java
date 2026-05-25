package com.payanigal.features.booking.detail;
import com.payanigal.data.dto.Booking;
import com.payanigal.data.dto.User;
import com.payanigal.data.repository.PayanigalDB;
import com.payanigal.util.ConsoleInput;
import com.payanigal.util.ParseHelper;
import java.util.Scanner;
public class BookingDetailView {
    private final BookingDetailModel model;
    private final Scanner scanner;
    public BookingDetailView(User currentUser) {
        this.model=new BookingDetailModel();
        this.scanner=ConsoleInput.getScanner();
    }
    public void init() {
        System.out.println();
        System.out.println("=== PNR / Booking Status ===");
        while (true) {
            System.out.print("Enter PNR (or 0 to go back): ");
            String input = scanner.nextLine().trim();
            if ("0".equals(input)) return;
            String err = model.validatePnr(input);
            if (err != null) { System.out.println("  Error: " + err); continue; }
            Booking booking = model.findByPnr(input);
            if (booking == null) { System.out.println("  No booking found for PNR: " + input.toUpperCase()); continue; }
            printDetail(booking);
            return;
        }
    }
    private void printDetail(Booking booking) {
        User u = PayanigalDB.getInstance().getUserById(booking.getUserId());
        System.out.println();
        System.out.println("============================================");
        System.out.println("  BOOKING DETAILS");
        System.out.println("============================================");
        System.out.println("  PNR        : " + booking.getPnr());
        System.out.println("  Status     : " + booking.getStatus().name());
        System.out.println("  Passenger  : " + (u != null ? u.getName() : "-"));
        System.out.println("  Route      : " + booking.getOrigin() + " -> " + booking.getDestination());
        System.out.println("  Date       : " + booking.getTravelDate());
        System.out.println("  Passengers : " + booking.getPassengerCount());
        System.out.println("  Total Fare : " + ParseHelper.formatFare(booking.getTotalFare()));
        System.out.println("  Booked At  : " + ParseHelper.formatDateTime(booking.getBookedAt()));
        if (booking.getStatus() == Booking.BookingStatus.CANCELLED) {
            System.out.println("  Cancelled  : " + ParseHelper.formatDateTime(booking.getCancelledAt()));
            if (booking.getCancellationReason() != null)
                System.out.println("  Reason     : " + booking.getCancellationReason());
        }
        System.out.println("============================================");
    }
}
