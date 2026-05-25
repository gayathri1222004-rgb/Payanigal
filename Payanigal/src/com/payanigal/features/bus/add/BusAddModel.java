package com.payanigal.features.bus.add;
import com.payanigal.data.dto.Bus;
import com.payanigal.data.repository.PayanigalDB;
import com.payanigal.util.ParseHelper;
import java.util.regex.Pattern;
class BusAddModel {
    private static final Pattern BUS_NUM_PATTERN = Pattern.compile("^[A-Z]{2}\\d{2}[A-Z]{1,3}\\d{4}$");
    private static final int MAX_SEATS = 60;
    private static final int MIN_SEATS = 10;
    private final BusAddView view;
    BusAddModel(BusAddView view) {
        this.view = view;
    }
    String validateBusNumber(String num) {
        if (num == null || num.trim().isEmpty()) return "Bus number cannot be empty.";
        String t = num.trim().toUpperCase();
        if (!BUS_NUM_PATTERN.matcher(t).matches())
            return "Invalid format. Example: KA01AB1234";
        for (Bus b : PayanigalDB.getInstance().getAllBuses()) {
            if (t.equals(b.getBusNumber())) return "Bus number already exists.";
        }
        return null;
    }
    String validateOperatorName(String name) {
        if (name == null || name.trim().isEmpty()) return "Operator name cannot be empty.";
        if (name.trim().length() < 3) return "Operator name must be at least 3 characters.";
        return null;
    }

    Bus.BusType parseBusType(String input) {
        if (input == null) return null;
        Integer n = ParseHelper.parsePositiveInt(input);
        if (n == null) return null;
        switch (n) {
            case 1: return Bus.BusType.AC_SLEEPER;
            case 2: return Bus.BusType.AC_SEATER;
            case 3: return Bus.BusType.NON_AC_SLEEPER;
            case 4: return Bus.BusType.NON_AC_SEATER;
            default: return null;
        }
    }

    String validateTotalSeats(String input) {
        Integer n = ParseHelper.parsePositiveInt(input);
        if (n == null)       return "Enter a valid number.";
        if (n < MIN_SEATS)   return "Minimum " + MIN_SEATS + " seats required.";
        if (n > MAX_SEATS)   return "Maximum " + MAX_SEATS + " seats allowed.";
        return null;
    }

    void addBus(String busNumber, String operatorName, Bus.BusType type, int totalSeats) {
        Bus bus = new Bus();
        bus.setBusNumber(busNumber.trim().toUpperCase());
        bus.setOperatorName(operatorName.trim());
        bus.setBusType(type);
        bus.setTotalSeats(totalSeats);
        bus.setStatus(Bus.BusStatus.ACTIVE);

        Bus saved =PayanigalDB.getInstance().addBus(bus);
        if (saved == null) {
            view.onAddFailed("Failed to add bus. Please try again.");
        } else {
            view.onAddSuccess(saved);
        }
    }
}
