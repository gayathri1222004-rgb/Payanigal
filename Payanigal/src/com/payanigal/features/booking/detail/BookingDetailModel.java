package com.payanigal.features.booking.detail;
import com.payanigal.data.dto.Booking;
import com.payanigal.data.repository.PayanigalDB;
import com.payanigal.data.repository.PayanigalDB;

class BookingDetailModel {

    Booking findByPnr(String pnr) {
        if (pnr == null || pnr.trim().isEmpty()) return null;
        return PayanigalDB.getInstance().getBookingByPnr(pnr.trim().toUpperCase());
    }

    String validatePnr(String pnr) {
        if (pnr == null || pnr.trim().isEmpty()) return "PNR cannot be empty.";
        if (!pnr.trim().toUpperCase().startsWith("BW")) return "Invalid PNR format (should start with BW).";
        return null;
    }
}
