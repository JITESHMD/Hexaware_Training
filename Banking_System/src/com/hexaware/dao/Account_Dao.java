package com.hexaware.dao;

import com.hexaware.util.DB_Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Data Access Object for handling Account-related database operations.
 */
public class Account_Dao {

    /**
     * Adds a new account record to the database.
     *
     * @param customerId  the id of the customer
     * @param accountType the type of account (e.g., Savings, Current)
     * @param balance     the initial balance of the account
     */
    public void addAccount(int customerId, String accountType, double balance) {
        String sql = "INSERT INTO Accounts (customer_id, account_type, balance) VALUES (?, ?, ?)";

        try (Connection conn = DB_Util.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, customerId);
            pstmt.setString(2, accountType);
            pstmt.setDouble(3, balance);

            int rowsInserted = pstmt.executeUpdate();
            System.out.println(rowsInserted + " account(s) successfully created.");
        } catch (Exception e) {
            System.err.println("Error while inserting account:");
            e.printStackTrace();
        }
    }

    /**
     * Displays all account records along with corresponding customer information.
     */
    public void viewAllAccounts() {
        String sql = "SELECT a.account_id, c.name AS customer_name, a.account_type, a.balance " +
                     "FROM Accounts a INNER JOIN Customers c ON a.customer_id = c.customer_id";

        try (Connection conn = DB_Util.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("Account Details:\n--------------------------");

            while (rs.next()) {
                System.out.println("Account ID     : " + rs.getInt("account_id"));
                System.out.println("Customer Name  : " + rs.getString("customer_name"));
                System.out.println("Account Type   : " + rs.getString("account_type"));
                System.out.println("Current Balance: $" + rs.getDouble("balance"));
                System.out.println("--------------------------");
            }

        } catch (Exception e) {
            System.err.println("Error retrieving account data:");
            e.printStackTrace();
        }
    }
}
