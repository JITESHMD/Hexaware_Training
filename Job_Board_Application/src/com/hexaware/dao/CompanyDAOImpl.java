package com.hexaware.dao;

import com.hexaware.entity.Company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyDAOImpl implements CompanyDAO {
    private Connection conn;

    public CompanyDAOImpl(Connection conn) {
        this.conn = conn;
    }

    // Insert a new company into the database
    @Override
    public void insertCompany(Company company) throws SQLException {
        String sql = "INSERT INTO Companies (companyname, location) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, company.getCompanyName());
            stmt.setString(2, company.getLocation());
            stmt.executeUpdate();
        }
    }

    // Fetch all companies from the database
    @Override
    public List<Company> getAllCompanies() throws SQLException {
        List<Company> companies = new ArrayList<>();
        String sql = "SELECT * FROM Companies";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Company company = new Company(
                    rs.getInt("companyid"),
                    rs.getString("companyname"),
                    rs.getString("location")  // Fetch location
                );
                companies.add(company);
            }
        }
        return companies;
    }
}
