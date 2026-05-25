package com.payanigal.util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
public class ParseHelper {
    private static final String DATE_PATTERN= "dd-MM-yyyy";
    private static final String DATETIME_PATTERN = "dd-MM-yyyy HH:mm";
    private static final String TIME_PATTERN = "HH:mm";
    private static final String EMPTY= "-";
    private ParseHelper() {}
    public static Integer parsePositiveInt(String input) {
        if (input == null) return null;
        String t = input.trim();
        if (t.isEmpty()) return null;
        long v = 0L;
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (c < '0' || c > '9') return null;
            v = v * 10 + (c - '0');
            if (v > Integer.MAX_VALUE) return null;
        }
        return (int) v;
    }
    public static Long parseDate(String input) {
        if (input == null || input.trim().isEmpty()) return null;
        SimpleDateFormat fmt = new SimpleDateFormat(DATE_PATTERN, Locale.ROOT);
        fmt.setLenient(false);
        try { return fmt.parse(input.trim()).getTime(); }
        catch (ParseException e) { return null; }
    }
    public static Long parseDateTime(String input) {
        if (input == null || input.trim().isEmpty()) return null;
        String t = input.trim();
        SimpleDateFormat fmt = new SimpleDateFormat(
                t.length() == 10 ? DATE_PATTERN : DATETIME_PATTERN, Locale.ROOT);
        fmt.setLenient(false);
        try { return fmt.parse(t).getTime(); }
        catch (ParseException e) { return null; }
    }
    public static boolean isYes(String input) {
        if (input == null) return false;
        String t = input.trim();
        return !t.isEmpty() && (t.charAt(0) == 'Y' || t.charAt(0) == 'y');
    }
    public static boolean isTodayOrFuture(long millis) {
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);
        return millis >= today.getTimeInMillis();
    }
    public static boolean isFutureDateTime(long millis) {
        return millis > System.currentTimeMillis();
    }
    public static String formatDate(Long millis) {
        if (millis == null) return EMPTY;
        return new SimpleDateFormat(DATE_PATTERN, Locale.ROOT).format(new Date(millis));
    }
    public static String formatTime(Long millis) {
        if (millis == null) return EMPTY;
        return new SimpleDateFormat(TIME_PATTERN, Locale.ROOT).format(new Date(millis));
    }
    public static String formatDateTime(Long millis) {
        if (millis == null) return EMPTY;
        return new SimpleDateFormat(DATETIME_PATTERN, Locale.ROOT).format(new Date(millis));
    }
    public static String formatDuration(long from, long to) {
        long diff = to - from;
        if (diff < 0) return EMPTY;
        long mins = diff / 60000;
        return (mins / 60) + "h " + (mins % 60) + "m";
    }
    public static String formatFare(double fare) {
        long l = Math.round(fare);
        StringBuilder sb = new StringBuilder(String.valueOf(l));
        sb.reverse();
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < sb.length(); i++) {
            if (i == 3 && sb.length() > 3) out.append(',');
            else if (i > 3 && (i - 3) % 2 == 0) out.append(',');
            out.append(sb.charAt(i));
        }
        return "Rs. " + out.reverse();
    }
    public static String trunc(String s, int max) {
        if (s == null) return EMPTY;
        return s.length() <= max ? s : s.substring(0, max - 1) + "~";
    }
}
