package com.hexaware.dao;

import com.hexaware.entity.JobListing;
import java.sql.SQLException;
import java.util.List;

public interface JobListingDAO {
    void insertJobListing(JobListing job) throws SQLException;
    List<JobListing> getJobListings() throws SQLException;
    List<JobListing> getJobListingsBySalaryRange(double minSalary, double maxSalary) throws SQLException;
}
