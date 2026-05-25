package com.payanigal.features.booking.list;
import com.payanigal.data.dto.Booking;
import com.payanigal.data.dto.User;
import com.payanigal.data.repository.PayanigalDB;
import com.payanigal.util.ParseHelper;
import java.util.List;
public class BookingListView {
    private final User currentUser;
    public BookingListView(User currentUser) { this.currentUser = currentUser; }
    public void init() {
        System.out.println();
        System.out.println("=== My Bookings ===");
        List<Booking> bookings = PayanigalDB.getInstance().getBookingsByUser(currentUser.getId());
        if (bookings.isEmpty()) { System.out.println("You have no bookings yet."); return; }
        printTable(bookings, false);
    }
    public void initAdmin() {
        System.out.println();
        System.out.println("=== All Bookings ===");
        List<Booking> bookings =PayanigalDB.getInstance().getAllBookings();
        if (bookings.isEmpty()) { System.out.println("No bookings in the system yet."); return; }
        printTable(bookings, true);
    }
    private void printTable(List<Booking> bookings, boolean showUser) {
        System.out.printf("%-14s %-24s %-12s %-6s %-12s %-12s %s%n",
                "PNR", "Route", "Date", "Pax", "Fare", "Status", showUser ? "Passenger" : "");
        System.out.println("-".repeat(showUser ? 100 : 85));
        for (Booking b : bookings) {
            String userCol = showUser
                    ? (PayanigalDB.getInstance().getUserById(b.getUserId()) != null
                    ? PayanigalDB.getInstance().getUserById(b.getUserId()).getName() : "-")
                    : "";
            System.out.printf("%-14s %-24s %-12s %-6d %-12s %-12s %s%n",
                    b.getPnr(),
                    ParseHelper.trunc(b.getOrigin() + " -> " + b.getDestination(), 23),
                    b.getTravelDate(), b.getPassengerCount(),
                    ParseHelper.formatFare(b.getTotalFare()),
                    b.getStatus().name(), userCol);
        }
    }
}
