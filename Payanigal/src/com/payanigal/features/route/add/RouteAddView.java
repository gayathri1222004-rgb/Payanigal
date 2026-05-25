package com.payanigal.features.route.add;
import com.payanigal.data.dto.Route;
import com.payanigal.util.ConsoleInput;
import java.util.Scanner;
public class RouteAddView {
    private final RouteAddModel model;
    private final Scanner scanner;
    public RouteAddView() {
        this.model = new RouteAddModel(this);
        this.scanner = ConsoleInput.getScanner();
    }


    private String promptCity(String label) {
        while (true) {
            System.out.print(label);
            String in  = scanner.nextLine();
            String err = model.validateCity(in);
            if (err == null) return in.trim();
            System.out.println("  Error: " + err);
        }
    }

    private String promptDestination(String origin) {
        while (true) {
            System.out.print("Destination City: ");
            String in  = scanner.nextLine();
            String e1  = model.validateCity(in);
            if (e1 != null) { System.out.println("  Error: " + e1); continue; }
            String e2 = model.validateSameCity(origin, in);
            if (e2 != null) { System.out.println("  Error: " + e2); continue; }
            return in.trim();
        }
    }

    private int promptDistance() {
        while (true) {
            System.out.print("Distance (km): ");
            String in  = scanner.nextLine();
            String err = model.validateDistance(in);
            if (err == null) return Integer.parseInt(in.trim());
            System.out.println("  Error: " + err);
        }
    }

    void onAddSuccess(Route route) {
        System.out.println("Route added: " + route.getDisplay()
                + " (" + route.getDistanceKm() + " km)  [ID: " + route.getId() + "]");
    }

    void onAddFailed(String message) {
        System.out.println("Error: " + message);
    }
    public void init() {
        System.out.println();
        System.out.println("=== Add New Route ===");
        String origin = promptCity("Origin City: ");
        String destination = promptDestination(origin);
        int distance= promptDistance();

        if (model.routeExists(origin, destination)) {
            System.out.println("Route " + origin + " -> " + destination + " already exists.");
            return;
        }
        model.addRoute(origin, destination, distance);
    }
}
