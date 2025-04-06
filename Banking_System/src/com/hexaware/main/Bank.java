package com.hexaware.main;


import java.util.logging.*;

import com.hexaware.entity.Current_Account;
import com.hexaware.entity.Customer;
import com.hexaware.util.DB_Util;

import java.sql.*;
import java.util.Scanner;

public class Bank {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Displaying the menu
            System.out.println("\n=== Bank Menu ===");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Get Balance");
            System.out.println("6. Account Details");
            System.out.println("7. List Accounts");
            System.out.println("8. Calculate Interest");
            System.out.println("9. Exit");

            // Take user input
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            // Switch case to handle different menu choices
            switch (choice) {
                case 1:
                    createAccount(scanner);
                    break;
                case 2:
                    deposit(scanner);
                    break;
                case 3:
                    withdraw(scanner);
                    break;
                case 4:
                    transfer(scanner);
                    break;
                case 5:
                    getBalance(scanner);
                    break;
                case 6:
                    accountDetails(scanner);
                    break;
                case 7:
                    listAccounts();
                    break;
                case 8:
                    calculateInterest(scanner);
                    break;
                case 9:
                    System.out.println("Exiting... Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    
    
    

    private static void createAccount(Scanner scanner) {
        try (Connection conn = DB_Util.getConnection()) {
            if (conn == null) {
                System.out.println("❌ Database connection failed.");
                return;
            }

            System.out.println("Enter first name:");
            String firstName = scanner.nextLine();
            System.out.println("Enter last name:");
            String lastName = scanner.nextLine();
            System.out.println("Enter DoB (yyyy-mm-dd): ");
            String DOB = scanner.nextLine();
            System.out.println("Enter email:");
            String email = scanner.nextLine();
            System.out.println("Enter phone:");
            String phone_number = scanner.nextLine();
            System.out.println("Enter address:");
            String address = scanner.nextLine();

            // Insert customer into the database
            String insertCustomerSQL = "INSERT INTO Customers (first_Name, last_Name, DOB,email, phone_number, address) VALUES (?,?, ?,?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insertCustomerSQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
                pstmt.setString(1, firstName);
                pstmt.setString(2, lastName);
                pstmt.setString(3, DOB);
                pstmt.setString(4, email);
                pstmt.setString(5, phone_number);
                pstmt.setString(6, address);

                int rowsInserted = pstmt.executeUpdate();

                if (rowsInserted > 0) {
                    ResultSet rs = pstmt.getGeneratedKeys();
                    if (rs.next()) {
                        int customerId = rs.getInt(1);
                        System.out.println("✅ Customer created with ID: " + customerId);
                        // Now create a current account for the customer
                        Current_Account account = new Current_Account(0.0f, new Customer(firstName, lastName, DOB, email, phone_number, address));
                        String insertAccountSQL = "INSERT INTO Accounts (customer_id, account_type, balance) VALUES (?, ?, ?)";
                        try (PreparedStatement pstmt2 = conn.prepareStatement(insertAccountSQL)) {
                            pstmt2.setInt(1, customerId);
                            System.out.println("Enter Account Type :");
                            String actype = scanner.nextLine();
                            pstmt2.setString(2, actype);
                            pstmt2.setFloat(3, account.getBalance());
                            pstmt2.executeUpdate();
                            System.out.println("✅ Account created successfully for customer ID: " + customerId);
                        }
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("❌ Exception occurred: " + e.getMessage());
        }
    }
    
    
    
    
    
    
    
    

    private static void deposit(Scanner scanner) {
        System.out.println("Enter the Account ID to deposit into:");
        long accountId = scanner.nextLong();
        System.out.println("Enter the amount to deposit:");
        float depositAmount = scanner.nextFloat();

        try (Connection conn = DB_Util.getConnection()) {
            if (conn == null) {
                System.out.println("❌ Database connection failed.");
                return;
            }

            // Retrieve current balance
            String getBalanceSQL = "SELECT balance FROM Accounts WHERE account_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(getBalanceSQL)) {
                pstmt.setLong(1, accountId);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    float currentBalance = rs.getFloat("balance");
                    float newBalance = currentBalance + depositAmount;

                    // Update balance after deposit
                    String updateBalanceSQL = "UPDATE Accounts SET balance = ? WHERE account_id = ?";
                    try (PreparedStatement pstmtUpdate = conn.prepareStatement(updateBalanceSQL)) {
                        pstmtUpdate.setFloat(1, newBalance);
                        pstmtUpdate.setLong(2, accountId);
                        pstmtUpdate.executeUpdate();
                        System.out.println("✅ Deposit successful! New balance: " + newBalance);
                    }
                } else {
                    System.out.println("❌ Account not found.");
                }
            }

        } catch (Exception e) {
            System.out.println("❌ Exception occurred: " + e.getMessage());
        }
    }

    
    
    
    
    
    
    
    private static void withdraw(Scanner scanner) {
        System.out.println("Enter the Account ID to withdraw from:");
        long accountId = scanner.nextLong();
        System.out.println("Enter the amount to withdraw:");
        float withdrawAmount = scanner.nextFloat();

        try (Connection conn = DB_Util.getConnection()) {
            if (conn == null) {
                System.out.println("❌ Database connection failed.");
                return;
            }

            // Retrieve current balance
            String getBalanceSQL = "SELECT balance FROM Accounts WHERE account_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(getBalanceSQL)) {
                pstmt.setLong(1, accountId);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    float currentBalance = rs.getFloat("balance");

                    if (currentBalance >= withdrawAmount) {
                        float newBalance = currentBalance - withdrawAmount;

                        // Update balance after withdrawal
                        String updateBalanceSQL = "UPDATE Accounts SET balance = ? WHERE account_id = ?";
                        try (PreparedStatement pstmtUpdate = conn.prepareStatement(updateBalanceSQL)) {
                            pstmtUpdate.setFloat(1, newBalance);
                            pstmtUpdate.setLong(2, accountId);
                            pstmtUpdate.executeUpdate();
                            System.out.println("✅ Withdrawal successful! New balance: " + newBalance);
                        }
                    } else {
                        System.out.println("❌ Insufficient balance for withdrawal.");
                    }
                } else {
                    System.out.println("❌ Account not found.");
                }
            }

        } catch (Exception e) {
            System.out.println("❌ Exception occurred: " + e.getMessage());
        }
    }
    
    
    
    
    

    private static void transfer(Scanner scanner) {
        System.out.println("Enter the source Account ID:");
        long sourceAccountId = scanner.nextLong();
        System.out.println("Enter the destination Account ID:");
        long destinationAccountId = scanner.nextLong();
        System.out.println("Enter the transfer amount:");
        float transferAmount = scanner.nextFloat();

        try (Connection conn = DB_Util.getConnection()) {
            if (conn == null) {
                System.out.println("❌ Database connection failed.");
                return;
            }

            // Retrieve current balance of source account
            String getSourceBalanceSQL = "SELECT balance FROM Accounts WHERE account_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(getSourceBalanceSQL)) {
                pstmt.setLong(1, sourceAccountId);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    float sourceBalance = rs.getFloat("balance");

                    if (sourceBalance >= transferAmount) {
                        // Withdraw from source account
                        String updateSourceBalanceSQL = "UPDATE Accounts SET balance = balance - ? WHERE account_id = ?";
                        try (PreparedStatement pstmtSource = conn.prepareStatement(updateSourceBalanceSQL)) {
                            pstmtSource.setFloat(1, transferAmount);
                            pstmtSource.setLong(2, sourceAccountId);
                            pstmtSource.executeUpdate();
                        }

                        // Deposit into destination account
                        String updateDestinationBalanceSQL = "UPDATE Accounts SET balance = balance + ? WHERE account_id = ?";
                        try (PreparedStatement pstmtDest = conn.prepareStatement(updateDestinationBalanceSQL)) {
                            pstmtDest.setFloat(1, transferAmount);
                            pstmtDest.setLong(2, destinationAccountId);
                            pstmtDest.executeUpdate();
                        }

                        System.out.println("✅ Transfer successful! " +
                                "Transferred " + transferAmount + " from account " + sourceAccountId +
                                " to account " + destinationAccountId);
                    } else {
                        System.out.println("❌ Insufficient balance in the source account.");
                    }
                } else {
                    System.out.println("❌ Source account not found.");
                }
            }

        } catch (Exception e) {
            System.out.println("❌ Exception occurred: " + e.getMessage());
        }
    }

    
    
    
    
    
    
    
    
    private static void getBalance(Scanner scanner) {
        System.out.println("Enter the Account ID to get balance:");
        long accountId = scanner.nextLong();

        try (Connection conn = DB_Util.getConnection()) {
            if (conn == null) {
                System.out.println("❌ Database connection failed.");
                return;
            }

            String getBalanceSQL = "SELECT balance FROM Accounts WHERE account_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(getBalanceSQL)) {
                pstmt.setLong(1, accountId);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    float balance = rs.getFloat("balance");
                    System.out.println("✅ Current balance for account " + accountId + ": " + balance);
                } else {
                    System.out.println("❌ Account not found.");
                }
            }

        } catch (Exception e) {
            System.out.println("❌ Exception occurred: " + e.getMessage());
        }
    }
    
    
    
    
    
    

    private static void accountDetails(Scanner scanner) {
        System.out.println("Enter the Account ID to get account details:");
        long accountId = scanner.nextLong();

        try (Connection conn = DB_Util.getConnection()) {
            if (conn == null) {
                System.out.println("❌ Database connection failed.");
                return;
            }

            String getAccountDetailsSQL = "SELECT * FROM Accounts WHERE account_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(getAccountDetailsSQL)) {
                pstmt.setLong(1, accountId);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    int customerId = rs.getInt("customer_id");
                    String accountType = rs.getString("account_type");
                    float balance = rs.getFloat("balance");
                    System.out.println("Account ID: " + accountId + " | Customer ID: " + customerId +
                            " | Account Type: " + accountType + " | Balance: " + balance);
                } else {
                    System.out.println("❌ Account not found.");
                }
            }

        } catch (Exception e) {
            System.out.println("❌ Exception occurred: " + e.getMessage());
        }
    }

    
    
    
    
    
    
    
    
    
    private static void listAccounts() {
        try (Connection conn = DB_Util.getConnection()) {
            if (conn == null) {
                System.out.println("❌ Database connection failed.");
                return;
            }

            String query = "SELECT * FROM Accounts";
            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
                System.out.println("📄 Accounts List:");
                while (rs.next()) {
                    long accountId = rs.getLong("account_id");
                    int customerId = rs.getInt("customer_id");
                    String accountType = rs.getString("account_type");
                    float balance = rs.getFloat("balance");
                    System.out.println("Account ID: " + accountId + " | Customer ID: " + customerId +
                            " | Type: " + accountType + " | Balance: " + balance);
                }
            }

        } catch (Exception e) {
            System.out.println("❌ Exception occurred: " + e.getMessage());
        }
    }

    
    
    
    
    private static void calculateInterest(Scanner scanner) {
        System.out.print("Enter Account ID: ");
        long accountId = scanner.nextLong();

        try (Connection conn = DB_Util.getConnection()) {
            String sql = "SELECT balance FROM Accounts WHERE account_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setLong(1, accountId);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    double principal = rs.getDouble("balance");

                    // example values
                    double rate = 3.5; // 3.5% interest rate
                    double time = 1;   // 1 year

                    double interest = (principal * rate * time) / 100;
                    double totalAmount = principal + interest;

                    System.out.printf("💰 Interest: %.2f\n", interest);
                    System.out.printf("📊 Total Balance after Interest: %.2f\n", totalAmount);
                } else {
                    System.out.println("❌ Account not found.");
                }
            }
        } catch (Exception e) {
            System.out.println("❌ Error calculating interest: " + e.getMessage());
        }
    }

    
    
    
    static {
        Logger.getLogger("com.mysql.cj").setLevel(Level.SEVERE);
    }
    
    
}