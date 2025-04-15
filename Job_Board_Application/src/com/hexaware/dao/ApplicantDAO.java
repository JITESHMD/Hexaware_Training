package com.hexaware.dao;

import com.hexaware.entity.Applicant;
import com.hexaware.exception.InvalidEmailFormatException;

import java.sql.SQLException;
import java.util.List;

public interface ApplicantDAO {
    void insertApplicant(Applicant applicant) throws SQLException, InvalidEmailFormatException;
    List<Applicant> getAllApplicants() throws SQLException;
}
