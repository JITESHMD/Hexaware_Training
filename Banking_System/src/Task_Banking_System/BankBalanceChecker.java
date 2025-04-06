package Task_Banking_System;


//Task 4: Looping, Array and Data Validation
//You are tasked with creating a program that allows bank customers to check their account balances.
//The program should handle multiple customer accounts, and the customer should be able to enter their
//account number, balance to check the balance.
//Tasks:
//1. Create a Java program that simulates a bank with multiple customer accounts.
//2. Use a loop (e.g., while loop) to repeatedly ask the user for their account number and
//balance until they enter a valid account number.
//3. Validate the account number entered by the user.
//4. If the account number is valid, display the account balance. If not, ask the user to try again.


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BankBalanceChecker {
    public static void main(String[] args) {
        // sample account data (account number -> balance)
        Map<String, Double> accounts = new HashMap<>();
        accounts.put("1001", 5000.75);
        accounts.put("1002", 12000.00);
        accounts.put("1003", 3500.50);
        accounts.put("1004", 7860.25);

        Scanner scanner = new Scanner(System.in);
        String input;

        System.out.println("=== Welcome to the Bank Balance Checker ===");

        while (true) {
            System.out.print("\nEnter your account number (or type 'exit' to quit): ");
            input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Thank you for using our service.");
                break;
            }

            if (accounts.containsKey(input)) {
                System.out.println("Account Number: " + input);
                System.out.println("Current Balance: $" + String.format("%.2f", accounts.get(input)));
            } else {
                System.out.println("Invalid account number. Please try again.");
            }
        }

        scanner.close();
    }
}


