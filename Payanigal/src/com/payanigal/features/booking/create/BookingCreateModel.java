package com.payanigal.features.booking.create;
import com.payanigal.data.dto.Booking;
import com.payanigal.data.dto.Route;
import com.payanigal.data.repository.PayanigalDB;
import com.payanigal.data.dto.User;
import com.payanigal.util.ParseHelper;
import java.util.List;
class BookingCreateModel {
    private static final int MAX_PASSENGERS = 6;
    private static final double FARE_PER_KM = 1.5;
    private final BookingCreateView view;
    BookingCreateModel(BookingCreateView view) { this.view = view; }
    List<String> getAllCities() { return PayanigalDB.getInstance().getAllCities(); }
    String validateCity(String city) {
        if (city == null || city.trim().isEmpty()) return "City cannot be empty.";
        return null;
    }
    String validateSameCity(String o, String d) {
        if (o != null && d != null && o.trim().equalsIgnoreCase(d.trim()))
            return "Origin and destination cannot be the same.";
        return null;
    }
    Long parseTravelDate(String input) {
        Long millis = ParseHelper.parseDate(input);
        if (millis == null || !ParseHelper.isTodayOrFuture(millis)) return null;
        return millis;
    }
    String validatePassengerCount(String input) {
        Integer n = ParseHelper.parsePositiveInt(input);
        if (n == null || n <= 0)
            return "Enter a valid positive number.";
        if (n > MAX_PASSENGERS)
            return "Maximum " + MAX_PASSENGERS + " passengers per booking.";
        return null;
    }
    double calculateFare(String origin, String destination, int passengers) {
        for (Route r : PayanigalDB.getInstance().getAllRoutes()) {
            if (r.getOrigin().equalsIgnoreCase(origin)
                    && r.getDestination().equalsIgnoreCase(destination))
                return r.getDistanceKm() * FARE_PER_KM * passengers;
        }
        return 0;
    }
    void confirmBooking(User user, String origin, String destination, String travelDate, int passengers) {
        double fare = calculateFare(origin, destination, passengers);
        Booking booking = new Booking();
        booking.setUserId(user.getId());
        booking.setOrigin(origin);
        booking.setDestination(destination);
        booking.setTravelDate(travelDate);
        booking.setPassengerCount(passengers);
        booking.setTotalFare(fare);
        Booking saved = PayanigalDB.getInstance().addBooking(booking);
        if (saved == null) { view.onBookingFailed("Booking could not be completed. Please try again."); return; }
        view.onBookingSuccess(saved);
    }
}
