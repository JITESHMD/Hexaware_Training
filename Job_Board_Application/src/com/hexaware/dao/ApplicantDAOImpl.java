package com.hexaware.dao;

import com.hexaware.entity.Applicant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ApplicantDAOImpl implements ApplicantDAO {
    private Connection conn;

    public ApplicantDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insertApplicant(Applicant applicant) throws SQLException {
        String sql = "INSERT INTO applicants (firstname, lastname, email, phone,resume) VALUES (?, ?, ?, ?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, applicant.getFirstName());
            stmt.setString(2, applicant.getLastName());
            stmt.setString(3, applicant.getEmail());
            stmt.setString(4, applicant.getPhone());
            stmt.setString(5, applicant.getResume());
            stmt.executeUpdate();
        }
    }


    @Override
    public List<Applicant> getAllApplicants() throws SQLException {
        // Implement logic to fetch all applicants
        return null;
    }

    // Other methods from ApplicantDAO interface
}
