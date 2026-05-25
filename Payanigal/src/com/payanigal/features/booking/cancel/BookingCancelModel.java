package com.payanigal.features.booking.cancel;
import com.payanigal.data.dto.Booking;
import com.payanigal.data.repository.PayanigalDB;
import java.util.ArrayList;
import java.util.List;
class BookingCancelModel {
    private final BookingCancelView view;
    BookingCancelModel(BookingCancelView view) { this.view = view; }
    List<Booking> getActiveBookingsForUser(Long userId) {
        List<Booking> active = new ArrayList<>();
        for (Booking b : PayanigalDB.getInstance().getBookingsByUser(userId))
            if (b.getStatus()== Booking.BookingStatus.CONFIRMED) active.add(b);
        return active;
    }
    String validateReason(String reason) {
        if (reason == null || reason.trim().isEmpty()) return "Please provide a cancellation reason.";
        if (reason.trim().length() < 5) return "Reason must be at least 5 characters.";
        return null;
    }
    void cancel(Booking booking, String reason) {
        booking.setCancellationReason(reason.trim());
        boolean ok = PayanigalDB.getInstance().cancelBooking(booking.getId());
        if (!ok) { view.onCancelFailed("Cancellation failed. Please try again."); return; }
        view.onCancelSuccess(booking);
    }
}
