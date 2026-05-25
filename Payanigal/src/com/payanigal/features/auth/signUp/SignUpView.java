package com.payanigal.features.auth.signUp;
import com.payanigal.data.dto.User;
import com.payanigal.features.auth.signIn.SignInView;
import com.payanigal.util.ConsoleInput;
import java.util.Scanner;
public class SignUpView {
    private final SignUpModel model;
    private final Scanner scanner;
    public SignUpView() {
        this.model=new SignUpModel(this);
        this.scanner=ConsoleInput.getScanner();
    }
    public void init() {
        System.out.println();
        System.out.println("=== Create Your Payanigal Account ===");
        String name = promptName();
        String email = promptEmail();
        String password = promptPassword();
        String mobile= promptMobile();
        model.register(name, email, password, mobile);
    }
    private String promptName() {
        while (true) {
            System.out.print("Full Name: ");
            String input = scanner.nextLine();
            String error = model.validateName(input);
            if (error == null) return input.trim();
            System.out.println("  Error: " + error);
        }
    }
    private String promptEmail() {
        while (true) {
            System.out.print("Email: ");
            String input = scanner.nextLine();
            String error = model.validateEmail(input);
            if (error == null) return input.trim();
            System.out.println("  Error: " + error);
        }
    }
    private String promptPassword() {
        while (true) {
            System.out.print("Password (min 8 chars, letters + digits): ");
            String input = scanner.nextLine();
            String error = model.validatePassword(input);
            if (error != null) { System.out.println("  Error: " + error); continue; }
            System.out.print("Confirm Password: ");
            String confirm = scanner.nextLine();
            String cError  = model.validateConfirmPassword(input, confirm);
            if (cError != null) { System.out.println("  Error: " + cError); continue; }
            return input;
        }
    }
    private String  promptMobile() {
        while (true) {
            System.out.print("Mobile Number (10 digits): ");
            String input = scanner.nextLine();
            String error = model.validateMobile(input);
            if (error == null) return input.trim();
            System.out.println("  Error: " + error);
        }
    }
    void onRegistrationSuccess(User user) {
        System.out.println();
        System.out.println("Account created successfully!");
        System.out.println("Your User ID: " + user.getUserId());
        System.out.println("Please sign in to continue.");
        new SignInView().init();
    }
    void onRegistrationFailed(String message) {
        System.out.println("Error: " + message);
    }
}
