package com.hexaware.util;

import com.hexaware.dao.*;
import com.hexaware.entity.*;
import com.hexaware.exception.DatabaseConnectionException;
import com.hexaware.exception.InvalidEmailFormatException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DatabaseManager {

    private JobListingDAO jobListingDAO;
    private CompanyDAO companyDAO;
    private ApplicantDAO applicantDAO;
    private JobApplicationDAO jobApplicationDAO;

    
    public DatabaseManager(Connection conn) throws SQLException {
        this.jobListingDAO = new JobListingDAOImpl(conn);
        this.companyDAO = new CompanyDAOImpl(conn);
        this.applicantDAO = new ApplicantDAOImpl(conn);
        this.jobApplicationDAO = new JobApplicationDAOImpl(conn);
    }

    // Insert a new job listing into the database
    public void insertJobListing(JobListing job) throws DatabaseConnectionException {
        try {
            jobListingDAO.insertJobListing(job);
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Failed to insert job listing", e);
        }
    }

    // Insert a new company into the database
    public void insertCompany(Company company) throws DatabaseConnectionException {
        try {
            companyDAO.insertCompany(company);
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Failed to insert company", e);
        }
    }

    // Insert a new applicant into the database
    public void insertApplicant(Applicant applicant) throws DatabaseConnectionException, InvalidEmailFormatException {
        try {
            applicantDAO.insertApplicant(applicant);
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Failed to insert applicant", e);
        }
    }

    // Insert a new job application into the database
    public void insertJobApplication(JobApplication jobApplication) throws DatabaseConnectionException {
        try {
            jobApplicationDAO.insertJobApplication(jobApplication);
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Failed to insert job application", e);
        }
    }

    // Get all job listings
    public List<JobListing> getAllJobListings() throws DatabaseConnectionException {
        try {
            return jobListingDAO.getJobListings();
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Failed to retrieve job listings", e);
        }
    }

    // Get all companies
    public List<Company> getAllCompanies() throws DatabaseConnectionException {
        try {
            return companyDAO.getAllCompanies();
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Failed to retrieve companies", e);
        }
    }

    // Get all applicants
    public List<Applicant> getAllApplicants() throws DatabaseConnectionException {
        try {
            return applicantDAO.getAllApplicants();
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Failed to retrieve applicants", e);
        }
    }

    // Get all job applications for a specific job
    public List<JobApplication> getApplicationsForJob(int jobID) throws DatabaseConnectionException {
        try {
            return jobApplicationDAO.getJobApplicationsForJob(jobID);
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Failed to retrieve job applications for job ID: " + jobID, e);
        }
    }

    // Get job listings within a specific salary range
    public List<JobListing> getJobListingsBySalaryRange(double minSalary, double maxSalary) throws DatabaseConnectionException {
        try {
            return jobListingDAO.getJobListingsBySalaryRange(minSalary, maxSalary);
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Failed to retrieve job listings in the specified salary range", e);
        }
    }
}
