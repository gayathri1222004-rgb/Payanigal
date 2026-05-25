package com.payanigal.features.bus.add;
import com.payanigal.data.dto.Bus;
import com.payanigal.util.ConsoleInput;
import java.util.Scanner;
public class BusAddView {
    private final BusAddModel model;
    private final Scanner scanner;
    public BusAddView() {
        this.model= new BusAddModel(this);
        this.scanner = ConsoleInput.getScanner();
    }
    public void init() {
        System.out.println();
        System.out.println("=== Add New Bus ===");
        String busNumber=promptBusNumber();
        String operatorName=promptOperatorName();
        Bus.BusType type=promptBusType();
        int totalSeats=promptTotalSeats();
        model.addBus(busNumber, operatorName, type, totalSeats);
    }
    private String promptBusNumber() {
        while (true) {
            System.out.print("Bus Registration Number (e.g. KA01AB1234): ");
            String in  = scanner.nextLine();
            String err = model.validateBusNumber(in);
            if (err == null) return in.trim().toUpperCase();
            System.out.println("  Error: " + err);
        }
    }

    private String promptOperatorName() {
        while (true) {
            System.out.print("Operator / Agency Name: ");
            String in  = scanner.nextLine();
            String err = model.validateOperatorName(in);
            if (err == null) return in.trim();
            System.out.println("  Error: " + err);
        }
    }

    private Bus.BusType promptBusType() {
        while (true) {
            System.out.println("Bus Type:");
            System.out.println("  1. AC Sleeper");
            System.out.println("  2. AC Seater");
            System.out.println("  3. Non-AC Sleeper");
            System.out.println("  4. Non-AC Seater");
            System.out.print("Choose (1-4): ");
            Bus.BusType t = model.parseBusType(scanner.nextLine());
            if (t != null) return t;
            System.out.println("  Error: Select a valid option (1-4).");
        }
    }
    private int promptTotalSeats() {
        while (true) {
            System.out.print("Total Seats (10-60): ");
            String in = scanner.nextLine();
            String err= model.validateTotalSeats(in);
            if (err == null) return Integer.parseInt(in.trim());
            System.out.println("  Error: " + err);
        }
    }
    void onAddSuccess(Bus bus) {
        System.out.println();
        System.out.println("Bus added successfully!");
        System.out.println("  ID         : " + bus.getId());
        System.out.println("  Bus Number : " + bus.getBusNumber());
        System.out.println("  Operator   : " + bus.getOperatorName());
        System.out.println("  Type       : " + Bus.labelFor(bus.getBusType()));
        System.out.println("  Seats      : " + bus.getTotalSeats());
    }

    void onAddFailed(String message) {
        System.out.println("Error: " + message);
    }
}
