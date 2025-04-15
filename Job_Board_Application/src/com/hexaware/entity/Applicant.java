package com.hexaware.entity;



public class Applicant {

    private int applicantID;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String resume;

    // Getters and Setters
    public int getApplicantID() {
        return applicantID;
    }

    public void setApplicantID(int applicantID) {
        this.applicantID = applicantID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    // Methods for creating profile and applying for a job
    public void createProfile(String email, String firstName, String lastName, String phone) {
        // Profile creation logic (to be handled in ApplicantDAO)
    }

    public void applyForJob(int jobID, String coverLetter) {
        // Job application logic (to be handled in JobApplicationDAO)
    }
}
