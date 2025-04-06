package Task_Banking_System;


//Task 5: Password Validation
//Write a program that prompts the user to create a password for their bank account. Implement if
//conditions to validate the password according to these rules:
//• The password must be at least 8 characters long.
//• It must contain at least one uppercase letter.
//• It must contain at least one digit.
//• Display appropriate messages to indicate whether their password is valid or not.

import java.util.Scanner;

public class PasswordValidator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Bank Account Password Setup ===");
        System.out.print("Enter a password: ");
        String password = scanner.nextLine();

        // validation checks
        boolean isValid = true;
        String message = "";

        if (password.length() < 8) {
            isValid = false;
            message += "- Password must be at least 8 characters long.\n";
        }

        if (!password.matches(".*[A-Z].*")) {
            isValid = false;
            message += "- Password must contain at least one uppercase letter.\n";
        }

        if (!password.matches(".*\\d.*")) {
            isValid = false;
            message += "- Password must contain at least one digit.\n";
        }

        // final output
        if (isValid) {
            System.out.println("Password is valid and accepted.");
        } else {
            System.out.println("Password is invalid:\n" + message);
        }

        scanner.close();
    }
}
 
    


