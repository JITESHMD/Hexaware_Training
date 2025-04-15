package com.hexaware.dao;

import com.hexaware.entity.JobListing;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobListingDAOImpl implements JobListingDAO {
    private Connection conn;

    public JobListingDAOImpl(Connection conn) {
        this.conn = conn;
    }

    // Insert a new job listing into the database
    @Override
    public void insertJobListing(JobListing job) throws SQLException {
        String sql = "INSERT INTO Jobs (CompanyID, JobTitle, JobDescription, JobLocation, Salary, JobType) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, job.getCompanyID());  // Set CompanyID
            stmt.setString(2, job.getJobTitle());  // Set JobTitle
            stmt.setString(3, job.getJobDescription());  // Set JobDescription
            stmt.setString(4, job.getJobLocation());  // Set JobLocation
            stmt.setDouble(5, job.getSalary());  // Set Salary
            stmt.setString(6, job.getJobType());  // Set JobType
            stmt.executeUpdate();
        }
    }

    // Get all job listings
    @Override
    public List<JobListing> getJobListings() throws SQLException {
        List<JobListing> listings = new ArrayList<>();
        String sql = "SELECT * FROM Jobs";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                JobListing job = new JobListing(
                    rs.getInt("CompanyID"),
                    rs.getString("JobTitle"),
                    rs.getString("JobDescription"),
                    rs.getString("JobLocation"),
                    rs.getDouble("Salary"),
                    rs.getString("JobType")
                );
                listings.add(job);
            }
        }
        return listings;
    }

    // Get job listings by salary range
    @Override
    public List<JobListing> getJobListingsBySalaryRange(double minSalary, double maxSalary) throws SQLException {
        List<JobListing> listings = new ArrayList<>();
        String sql = "SELECT * FROM Jobs WHERE Salary BETWEEN ? AND ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, minSalary);
            stmt.setDouble(2, maxSalary);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                JobListing job = new JobListing(
                    rs.getInt("CompanyID"),
                    rs.getString("JobTitle"),
                    rs.getString("JobDescription"),
                    rs.getString("JobLocation"),
                    rs.getDouble("Salary"),
                    rs.getString("JobType")
                );
                listings.add(job);
            }
        }
        return listings;
    }
}
