package com.payanigal.features.auth.signIn;
import com.payanigal.data.dto.User;
import com.payanigal.features.auth.signUp.SignUpView;
import com.payanigal.features.home.HomeView;
import com.payanigal.util.ConsoleInput;
import java.util.Scanner;
public class SignInView {
    private final SignInModel model;
    private final Scanner scanner;
    private boolean authenticated = false;
    public SignInView() {
        this.model = new SignInModel(this);
        this.scanner= ConsoleInput.getScanner();
    }
    public void init() {
        System.out.println();
        System.out.println("=== Sign In to BusWay ===");
        while (!authenticated) {
            System.out.print("Email: ");
            String email = scanner.nextLine().trim();
            System.out.print("Password: ");
            String password = scanner.nextLine();
            model.authenticate(email, password);
            if (authenticated) return;
            if (!handleFailureMenu()) return;
        }
    }
    private boolean handleFailureMenu() {
        while (true) {
            System.out.println();
            System.out.println("1. Retry");
            System.out.println("2. Sign Up");
            System.out.println("3. Return to main menu");
            System.out.print("Choose an option: ");
            switch (scanner.nextLine().trim()) {
                case "1": return true;
                case "2": new SignUpView().init(); return false;
                case "3": return false;
                default:  System.out.println("Invalid option.");
            }
        }
    }
    void onSignInSuccess(User user) {
        authenticated = true;
        System.out.println();
        System.out.println("Welcome, " + user.getName() + "!");
        new HomeView(user).init();
    }
    void onSignInFailed(String message) {
        System.out.println("Error: " + message);
    }
}
