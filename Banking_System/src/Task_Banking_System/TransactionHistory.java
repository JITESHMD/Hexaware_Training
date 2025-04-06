package Task_Banking_System;

//Task 6: Transaction History
//Create a program that maintains a list of bank transactions (deposits and withdrawals) for a customer.
//Use a while loop to allow the user to keep adding transactions until they choose to exit. Display the
//transaction history upon exit using looping statements.

import java.util.ArrayList;
import java.util.Scanner;

public class TransactionHistory {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> transactionHistory = new ArrayList<>();
        double balance = 0.0;
        boolean running = true;

        System.out.println("=== Welcome to the Bank Transaction System ===");

        while (running) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Exit");
            System.out.print("Enter your choice (1-3): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter deposit amount: ");
                    double deposit = scanner.nextDouble();
                    balance += deposit;
                    transactionHistory.add("Deposited: $" + deposit);
                    break;
                case 2:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawal = scanner.nextDouble();
                    if (withdrawal <= balance) {
                        balance -= withdrawal;
                        transactionHistory.add("Withdrawn: $" + withdrawal);
                    } else {
                        System.out.println(" Insufficient balance.");
                    }
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println(" Invalid choice. Try again.");
            }
        }

        // display transaction history
        System.out.println("\n=== Transaction History ===");
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions made.");
        } else {
            for (String transaction : transactionHistory) {
                System.out.println(transaction);
            }
            System.out.println("Current Balance: $" + balance);
        }

        scanner.close();
    }
}

