package com.hexaware.dao;

import com.hexaware.entity.Customer;
import com.hexaware.util.DB_Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Customer_Dao_Implementation implements Customer_Dao {

    // Insert a new customer into the database
    @Override
    public void insertCustomer(Customer customer) throws Exception {
        String sql = "INSERT INTO Customers (firstName,lastName, DOB,email, phone_number, address) VALUES (?,?, ?,?, ?, ?)";

        // Try-with-resources to ensure resources are closed
        try (Connection conn = DB_Util.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, customer.getFirstName()); // Combining first and last name
            ps.setString(2, customer.getLastName());
            ps.setString(3, customer.getDOB());
            ps.setString(3, customer.getEmail());
            ps.setString(4, customer.getPhone_number());
            ps.setString(5, customer.getAddress());

            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("✅ Customer inserted successfully.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Error inserting customer: " + e.getMessage());
            throw e;
        }
    }

    // Retrieve all customers from the database
    @Override
    public List<Customer> getAllCustomers() throws Exception {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM Customers"; // Assuming customer_id is auto-incremented

        // Try-with-resources to handle closing of resources automatically
        try (Connection conn = DB_Util.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Customer customer = new Customer(sql, sql, sql, sql, sql, sql);
                customer.setCustomerId(rs.getInt("customer_id"));
                customer.setFirstName(rs.getString("name")); // Extract first name
                customer.setLastName(rs.getString("name")); // Extract last name
                customer.setDOB(rs.getString("DOB"));
                customer.setEmail(rs.getString("email"));
                customer.setPhone_number(rs.getString("phone_number"));
                customer.setAddress(rs.getString("address"));

                customers.add(customer);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error retrieving customers: " + e.getMessage());
            throw e;
        }

        return customers;
    }

}