package com.hexaware.dao;

import com.hexaware.entity.JobApplication;
import java.sql.SQLException;
import java.util.List;

public interface JobApplicationDAO {
    void insertJobApplication(JobApplication application) throws SQLException;
    List<JobApplication> getJobApplicationsForJob(int jobID) throws SQLException;
}
