package com.hexaware.dao;

import com.hexaware.entity.JobApplication;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobApplicationDAOImpl implements JobApplicationDAO {
    private Connection conn;

    public JobApplicationDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insertJobApplication(JobApplication application) throws SQLException {
        String sql = "INSERT INTO JobApplications (applicantid, jobid, coverletter, applicationdate) VALUES (?, ?, ?, CURRENT_DATE)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, application.getApplicantID());
            stmt.setInt(2, application.getJobID());
            stmt.setString(3, application.getCoverLetter());
            stmt.executeUpdate();
        }
    }

    @Override
    public List<JobApplication> getJobApplicationsForJob(int jobID) throws SQLException {
        List<JobApplication> applications = new ArrayList<>();
        String sql = "SELECT * FROM JobApplications WHERE jobid = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, jobID);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    JobApplication app = new JobApplication(
                        rs.getInt("applicationid"),
                        rs.getInt("jobid"),
                        rs.getInt("applicantid"),
                        rs.getString("coverletter"),
                        rs.getDate("applicationdate")
                    );
                    applications.add(app);
                }
            }
        }
        return applications;
    }
}
