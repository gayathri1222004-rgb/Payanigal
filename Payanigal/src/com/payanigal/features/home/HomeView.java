package com.payanigal.features.home;
import com.payanigal.data.dto.User;
import com.payanigal.features.booking.cancel.BookingCancelView;
import com.payanigal.features.booking.create.BookingCreateView;
import com.payanigal.features.booking.detail.BookingDetailView;
import com.payanigal.features.booking.list.BookingListView;
import com.payanigal.features.bus.add.BusAddView;
import com.payanigal.features.bus.list.BusListView;
import com.payanigal.features.route.add.RouteAddView;
import com.payanigal.features.route.list.RouteListView;
import com.payanigal.util.ConsoleInput;
import java.util.Scanner;
public class HomeView {
    private final User user;
    private final Scanner scanner;
    public HomeView(User user) {
        this.user=user;
        this.scanner = ConsoleInput.getScanner();
    }
    public void init() {
        if (user == null || user.getRole() == null) {
            System.out.println("Error: Unable to determine your role. Please contact support.");
            return;
        }
        if (user.getRole() == User.Role.ADMIN)
            showAdminMenu();
        else
            showPassengerMenu();
    }
    private void showPassengerMenu() {
        while (true) {
            System.out.println();
            System.out.println("--- Passenger Menu (" + user.getName() + ") ---");
            System.out.println("1. Book a Ticket");
            System.out.println("Origin-Hyderabad    Destination-Bangalore");
            System.out.println("Origin-Hyderabad    Destination-Chennai");
            System.out.println("Origin-Bangalore    Destination-Mumbai");
            System.out.println("Origin-Chennai      Destination-Coimbatore");
            System.out.println("Origin-Mumbai       Destination-Pune");
            System.out.println("Origin-Hyderabad    Destination-Mumbai");
            System.out.println("2. My Bookings");
            System.out.println("3. View Booking / PNR Status");
            System.out.println("4. Cancel Booking");
            System.out.println("5. Sign Out");
            System.out.print("Choose an option: ");
            switch (scanner.nextLine().trim()) {
                case "1":
                    new BookingCreateView(user).init();
                    break;
                case "2":
                    new BookingListView(user).init();
                    break;
                case "3":
                    new BookingDetailView(user).init();
                    break;
                case "4":
                    new BookingCancelView(user).init();
                    break;
                case "5":
                    System.out.println("You have been signed out. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
    private void showAdminMenu() {
        while (true) {
            System.out.println();
            System.out.println("--- Admin Menu (" + user.getName() + ") ---");
            System.out.println("1. Add Bus");
            System.out.println("2. View All Buses");
            System.out.println("3. Add Route");
            System.out.println("4. View All Routes");
            System.out.println("5. View All Bookings");
            System.out.println("6. Sign Out");
            System.out.print("Choose an option: ");
            switch (scanner.nextLine().trim()) {
                case "1":
                    new BusAddView().init();
                    break;
                case "2":
                    new BusListView().init();
                    break;
                case "3":
                    new RouteAddView().init();
                    break;
                case "4":
                    new RouteListView().init();
                    break;
                case "5":
                    new BookingListView(user).initAdmin();
                    break;
                case "6":
                    System.out.println("You have been signed out. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
