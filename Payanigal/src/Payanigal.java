import java.util.Scanner;
import com.payanigal.features.auth.signIn.SignInView;
import com.payanigal.features.auth.signUp.SignUpView;
import com.payanigal.util.ConsoleInput;
public class Payanigal {
    public static final String VERSION_NAME = "1.0.0";
    public static final int VERSION_CODE = 1;
    public static void main(String[] args) {
        System.out.println(" ");
        System.out.println(" Welcome to Payanigal Reservation System   ");
        showLandingMenu();
    }
    private static void showLandingMenu() {
        Scanner scanner = ConsoleInput.getScanner();
        while (true) {
            System.out.println();
            System.out.println("1. Sign Up");
            System.out.println("2. Sign In");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    new SignUpView().init();
                    break;
                case "2":
                    new SignInView().init();
                    break;
                case "3":
                    System.out.println("Thank you for using BusWay. Have a safe journey!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
