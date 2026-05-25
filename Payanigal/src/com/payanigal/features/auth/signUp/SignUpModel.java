package com.payanigal.features.auth.signUp;
import com.payanigal.data.dto.User;
import com.payanigal.data.repository.PayanigalDB;
import com.payanigal.util.ParseHelper;
import java.util.regex.Pattern;
class SignUpModel {
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9._%+\\-]+@[A-Za-z0-9.\\-]+\\.[A-Za-z]{2,}$");
    private static final Pattern MOBILE_PATTERN  = Pattern.compile("^[6-9]\\d{9}$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d).{8,}$");
    private static final int MIN_NAME = 3;
    private static final int MAX_NAME = 50;
    private static final int MIN_AGE  = 18;
    private final SignUpView view;
    SignUpModel(SignUpView view) {
        this.view = view;
    }
    String validateName(String name) {
        if (name == null || name.trim().isEmpty()) return "Name cannot be empty.";
        String t = name.trim();
        if (t.length() < MIN_NAME || t.length() > MAX_NAME)
            return "Name must be " + MIN_NAME + "-" + MAX_NAME + " characters.";
        return null;
    }
    String validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) return "Email cannot be empty.";
        if (!EMAIL_PATTERN.matcher(email.trim()).matches()) return "Enter a valid email address.";
        if (PayanigalDB.getInstance().getUserByEmail(email.trim()) != null) return "Email is already registered.";
        return null;
    }
    String validatePassword(String password) {
        if (password == null || password.isEmpty()) return "Password cannot be empty.";
        if (!PASSWORD_PATTERN.matcher(password).matches())
            return "Password must be at least 8 characters with letters and digits.";
        return null;
    }
    String validateConfirmPassword(String password, String confirm) {
        if (confirm == null || !confirm.equals(password)) return "Passwords do not match.";
        return null;
    }
    String validateMobile(String mobile) {
        if (mobile == null || mobile.trim().isEmpty()) return "Mobile number cannot be empty.";
        if (!MOBILE_PATTERN.matcher(mobile.trim()).matches())
            return "Enter a valid 10-digit mobile number starting with 6-9.";
        return null;
    }
    Long parseDob(String input) {
        Long millis = ParseHelper.parseDate(input);
        if (millis == null) return null;
        if (millis >= System.currentTimeMillis()) return null;
        return millis;
    }
    boolean isFirstUser() {
        return PayanigalDB.getInstance().getAllUsers().isEmpty();
    }
    void register(String name, String email, String password, String mobile) {
        User user = new User();
        user.setName(name.trim());
        user.setEmail(email.trim().toLowerCase());
        user.setPassword(password);
        user.setMobileNo(mobile.trim());
        user.setRole(isFirstUser() ? User.Role.ADMIN : User.Role.PASSENGER);
        User saved = PayanigalDB.getInstance().addUser(user);
        if (saved == null) {
            view.onRegistrationFailed("Registration failed. Please try again.");
        } else {
            view.onRegistrationSuccess(saved);
        }
    }
}
