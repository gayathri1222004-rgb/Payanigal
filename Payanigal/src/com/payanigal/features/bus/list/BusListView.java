package com.payanigal.features.bus.list;
import com.payanigal.data.dto.Bus;
import com.payanigal.data.repository.PayanigalDB;
import com.payanigal.util.ParseHelper;
import java.util.List;
public class BusListView {
    public void init() {
        System.out.println();
        System.out.println("=== All Buses ===");
        List<Bus> buses = PayanigalDB.getInstance().getAllBuses();
        if (buses.isEmpty()) {
            System.out.println("No buses registered yet.");
            return; }
        System.out.printf("%-5s %-14s %-25s %-20s %-8s %s%n",
                "ID", "Bus Number", "Operator", "Type", "Seats", "Status");
        System.out.println("-".repeat(85));
        for (Bus b : buses)
            System.out.printf("%-5d %-14s %-25s %-20s %-8d %s%n",
                    b.getId(), b.getBusNumber(), ParseHelper.trunc(b.getOperatorName(), 24),
                    Bus.labelFor(b.getBusType()), b.getTotalSeats(), b.getStatus().name());
        System.out.println("Total: " + buses.size() + " buses");
    }
}
