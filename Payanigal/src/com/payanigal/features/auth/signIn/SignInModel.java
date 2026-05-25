package com.payanigal.features.auth.signIn;
import com.payanigal.data.dto.User;
import com.payanigal.data.repository.PayanigalDB;
import java.util.regex.Pattern;
class SignInModel {
    private static final Pattern EMAIL_RE = Pattern.compile("^[A-Za-z0-9._%+\\-]+@[A-Za-z0-9.\\-]+\\.[A-Za-z]{2,}$");
    private final SignInView view;
    SignInModel(SignInView view) { this.view = view; }
    String validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) return "Email cannot be empty.";
        if (!EMAIL_RE.matcher(email.trim()).matches()) return "Enter a valid email address.";
        return null;
    }
    String validatePassword(String password) {
        if (password == null || password.isEmpty()) return "Password cannot be empty.";
        return null;
    }
    void authenticate(String email, String password) {
        String emailErr = validateEmail(email);
        if (emailErr != null) { view.onSignInFailed(emailErr); return; }
        String passErr = validatePassword(password);
        if (passErr != null) { view.onSignInFailed(passErr); return; }
        User user = PayanigalDB.getInstance().authenticate(
                email.trim().toLowerCase(), password);
        if (user == null) { view.onSignInFailed("Invalid email or password."); return; }
        if (user.getStatus() == User.Status.INACTIVE) {
            view.onSignInFailed("Your account is inactive. Please contact support.");
            return;
        }
        view.onSignInSuccess(user);
    }
}