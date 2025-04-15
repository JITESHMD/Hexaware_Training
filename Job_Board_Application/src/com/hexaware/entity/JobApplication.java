package com.hexaware.entity;

import java.sql.Date;

public class JobApplication {

    private int applicationID;
    private int jobID;
    private int applicantID;
    private Date applicationDate;
    private String coverLetter;

    // Updated Constructor (matches the database columns)
    public JobApplication(int applicationID, int jobID, int applicantID, String coverLetter, Date applicationDate) {
        this.applicationID = applicationID;
        this.jobID = jobID;
        this.applicantID = applicantID;
        this.coverLetter = coverLetter;
        this.applicationDate = applicationDate;
    }

    // Getters and Setters
    public int getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(int applicationID) {
        this.applicationID = applicationID;
    }

    public int getJobID() {
        return jobID;
    }

    public void setJobID(int jobID) {
        this.jobID = jobID;
    }

    public int getApplicantID() {
        return applicantID;
    }

    public void setApplicantID(int applicantID) {
        this.applicantID = applicantID;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getCoverLetter() {
        return coverLetter;
    }

    public void setCoverLetter(String coverLetter) {
        this.coverLetter = coverLetter;
    }
}
