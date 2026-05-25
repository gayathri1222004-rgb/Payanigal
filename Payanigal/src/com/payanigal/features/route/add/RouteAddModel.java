package com.payanigal.features.route.add;
import com.payanigal.data.dto.Route;
import com.payanigal.data.repository.PayanigalDB;
import com.payanigal.util.ParseHelper;

class RouteAddModel {

    private final RouteAddView view;

    RouteAddModel(RouteAddView view) {
        this.view = view;
    }

    String validateCity(String city) {
        if (city == null || city.trim().isEmpty()) return "City name cannot be empty.";
        if (city.trim().length() < 3) return "City name must be at least 3 characters.";
        return null;
    }

    String validateSameCity(String origin, String destination) {
        if (origin != null && destination != null
                && origin.trim().equalsIgnoreCase(destination.trim()))
            return "Origin and destination cannot be the same.";
        return null;
    }

    String validateDistance(String input) {
        Integer n = ParseHelper.parsePositiveInt(input);
        if (n == null || n <= 0) return "Enter a valid positive distance.";
        if (n > 5000)            return "Distance cannot exceed 5000 km.";
        return null;
    }

    boolean routeExists(String origin, String destination) {
        for (Route r : PayanigalDB.getInstance().getAllRoutes()) {
            if (r.getOrigin().equalsIgnoreCase(origin.trim())
                    && r.getDestination().equalsIgnoreCase(destination.trim()))
                return true;
        }
        return false;
    }

    void addRoute(String origin, String destination, int distanceKm) {
        Route route = new Route();
        route.setOrigin(origin.trim());
        route.setDestination(destination.trim());
        route.setDistanceKm(distanceKm);
        Route saved = PayanigalDB.getInstance().addRoute(route);
        if (saved == null) {
            view.onAddFailed("Failed to add route. Please try again.");
        } else {
            view.onAddSuccess(saved);
        }
    }
}
