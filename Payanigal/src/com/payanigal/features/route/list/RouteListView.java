package com.payanigal.features.route.list;
import com.payanigal.data.dto.Route;
import com.payanigal.data.repository.PayanigalDB;
import java.util.List;
public class RouteListView {
    public void init() {
        System.out.println();
        System.out.println("=== All Routes ===");
        List<Route> routes = PayanigalDB.getInstance().getAllRoutes();
        if (routes.isEmpty()) { System.out.println("No routes added yet."); return; }
        System.out.printf("%-5s %-20s %-20s %s%n", "ID", "Origin", "Destination", "Distance (km)");
        System.out.println("-".repeat(55));
        for (Route r : routes)
            System.out.printf("%-5d %-20s %-20s %d km%n",
                    r.getId(), r.getOrigin(), r.getDestination(), r.getDistanceKm());
        System.out.println("Total: " + routes.size() + " routes");
    }
}
