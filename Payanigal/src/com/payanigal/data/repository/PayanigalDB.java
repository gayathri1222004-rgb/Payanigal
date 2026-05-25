package com.payanigal.data.repository;
import com.payanigal.data.dto.Booking;
import com.payanigal.data.dto.Bus;
import com.payanigal.data.dto.Route;
import com.payanigal.data.dto.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
public class PayanigalDB {
    private static PayanigalDB instance = null;
    private PayanigalDB() { seedData(); }
    public static PayanigalDB getInstance() {
        if (instance == null) instance = new PayanigalDB();
        return instance;
    }
    private final List<User> users= new ArrayList<>();
    private final List<Bus> buses= new ArrayList<>();
    private final List<Route> routes= new ArrayList<>();
    private final List<Booking> bookings = new ArrayList<>();
    private long userPk =  0L;
    private long busPk = 0L;
    private long routePk = 0L;
    private long bookingPk = 0L;
    private void seedData() {
        User admin = new User();
        admin.setName("Gayu");
        admin.setEmail("gayathri1222004@gmail.com");
        admin.setPassword("Gayathri@123");
        admin.setMobileNo("9000000000");
        admin.setRole(User.Role.ADMIN);
        addUser(admin);
        Bus b1 = new Bus();
        b1.setBusNumber("KA01AB1234");
        b1.setOperatorName("VRL Travels");
        b1.setBusType(Bus.BusType.AC_SLEEPER);
        b1.setTotalSeats(40);
        Bus b2 = new Bus();
        b2.setBusNumber("TN02CD5678");
        b2.setOperatorName("SRS Travels");
        b2.setBusType(Bus.BusType.AC_SEATER);
        b2.setTotalSeats(50);
        Bus b3 = new Bus();
        b3.setBusNumber("AP03EF9012");
        b3.setOperatorName("Surya Travels");
        b3.setBusType(Bus.BusType.NON_AC_SLEEPER);
        b3.setTotalSeats(36);
        Bus b4 = new Bus();
        b4.setBusNumber("MH04GH3456");
        b4.setOperatorName("Dhakshana Travels");
        b4.setBusType(Bus.BusType.NON_AC_SEATER);
        b4.setTotalSeats(54);
        addBus(b1);
        addBus(b2);
        addBus(b3);
        addBus(b4);
        Route r1 = new Route();
        r1.setOrigin("Hyderabad"); r1.setDestination("Bangalore");
        r1.setDistanceKm(570);
        Route r2 = new Route();
        r2.setOrigin("Hyderabad"); r2.setDestination("Chennai");
        r2.setDistanceKm(625);
        Route r3 = new Route();
        r3.setOrigin("Bangalore"); r3.setDestination("Mumbai");
        r3.setDistanceKm(980);
        Route r4 = new Route();
        r4.setOrigin("Chennai");   r4.setDestination("Coimbatore");
        r4.setDistanceKm(495);
        Route r5 = new Route();
        r5.setOrigin("Mumbai");    r5.setDestination("Pune");
        r5.setDistanceKm(150);
        Route r6 = new Route();
        r6.setOrigin("Hyderabad"); r6.setDestination("Mumbai");
        r6.setDistanceKm(710);
        addRoute(r1);
        addRoute(r2);
        addRoute(r3);
        addRoute(r4);
        addRoute(r5);
        addRoute(r6);
    }
    public User addUser(User user) {
        if (user == null || user.getEmail() == null || user.getEmail().trim().isEmpty()) return null;
        userPk++;
        user.setId(userPk);
        user.setUserId(String.format(Locale.ROOT, "USR%05d", userPk));
        if (user.getCreatedAt() == null) user.setCreatedAt(System.currentTimeMillis());
        if (user.getStatus()== null) user.setStatus(User.Status.ACTIVE);
        if (user.getRole()== null) user.setRole(User.Role.PASSENGER);
        users.add(user);
        return user;
    }
    public User getUserByEmail(String email) {
        if (email == null) return null;
        String key = email.trim().toLowerCase(Locale.ROOT);
        for (User u : users)
            if (u.getEmail() != null && u.getEmail().trim().toLowerCase(Locale.ROOT).equals(key)) return u;
        return null;
    }
    public User authenticate(String email, String password) {
        User u = getUserByEmail(email);
        if (u==null||password==null||!password.equals(u.getPassword())) return null;
        return u;
    }

    public User getUserById(Long id) {
        if (id == null) return null;
        for (User u : users) if (id.equals(u.getId())) return u;
        return null;
    }

    public List<User> getAllUsers() { return new ArrayList<>(users); }
    public Bus addBus(Bus bus) {
        if (bus == null) return null;
        busPk++;
        bus.setId(busPk);
        if (bus.getStatus() == null) bus.setStatus(Bus.BusStatus.ACTIVE);
        buses.add(bus);
        return bus;
    }

    public Bus getBusById(Long id) {
        if (id == null) return null;
        for (Bus b : buses) if (id.equals(b.getId())) return b;
        return null;
    }

    public List<Bus> getAllBuses()    {
        return new ArrayList<>(buses);
    }
    public List<Bus> getActiveBuses() {
        List<Bus> result = new ArrayList<>();
        for (Bus b : buses) if (b.getStatus() == Bus.BusStatus.ACTIVE) result.add(b);
        return result;
    }
    public Route addRoute(Route route) {
        if (route == null) return null;
        routePk++;
        route.setId(routePk);
        routes.add(route);
        return route;
    }

    public Route getRouteById(Long id) {
        if (id == null) return null;
        for (Route r : routes) if (id.equals(r.getId())) return r;
        return null;
    }

    public List<Route> getAllRoutes() { return new ArrayList<>(routes); }

    public List<String> getAllCities() {
        List<String> cities = new ArrayList<>();
        for (Route r : routes) {
            if (!cities.contains(r.getOrigin())) cities.add(r.getOrigin());
            if (!cities.contains(r.getDestination())) cities.add(r.getDestination());
        }
        java.util.Collections.sort(cities);
        return cities;
    }
    public Booking addBooking(Booking booking) {
        if (booking == null || booking.getUserId() == null) return null;
        bookingPk++;
        booking.setId(bookingPk);
        booking.setPnr(String.format(Locale.ROOT, "BW%08d", bookingPk));
        if (booking.getBookedAt() == null) booking.setBookedAt(System.currentTimeMillis());
        if (booking.getStatus()   == null) booking.setStatus(Booking.BookingStatus.CONFIRMED);
        bookings.add(booking);
        return booking;
    }
    public Booking getBookingById(Long id) {
        if (id == null) return null;
        for (Booking b : bookings) if (id.equals(b.getId())) return b;
        return null;
    }
    public Booking getBookingByPnr(String pnr) {
        if (pnr == null || pnr.trim().isEmpty()) return null;
        String key = pnr.trim().toUpperCase(Locale.ROOT);
        for (Booking b : bookings) if (key.equals(b.getPnr())) return b;
        return null;
    }
    public List<Booking> getBookingsByUser(Long userId) {
        List<Booking> result = new ArrayList<>();
        if (userId == null) return result;
        for (Booking b : bookings) if (userId.equals(b.getUserId())) result.add(b);
        return result;
    }
    public List<Booking> getAllBookings() { return new ArrayList<>(bookings); }
    public boolean cancelBooking(Long bookingId) {
        Booking b = getBookingById(bookingId);
        if (b == null || b.getStatus() == Booking.BookingStatus.CANCELLED) return false;
        b.setStatus(Booking.BookingStatus.CANCELLED);
        b.setCancelledAt(System.currentTimeMillis());
        return true;
    }
}
