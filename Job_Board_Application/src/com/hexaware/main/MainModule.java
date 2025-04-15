package com.hexaware.main;

import com.hexaware.dao.*;
import com.hexaware.entity.*;
import com.hexaware.exception.*;
import com.hexaware.util.DBConfig;
import com.hexaware.util.DatabaseManager;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class MainModule {

    private static Scanner scanner = new Scanner(System.in);
    
    // Database connection
    private static Connection conn;
    
    static {
        try {
            
            conn = DBConfig.getConnection();
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
            System.exit(1);
        }
    }

    public static void main(String[] args) throws SQLException {
        int choice;
        
        do {
            System.out.println("Job Board System");
            System.out.println("1. Register as Applicant");
            System.out.println("2. Insert a New Company");
            System.out.println("3. Post a Job (Company)");
            System.out.println("4. Apply for a Job");
            System.out.println("5. View Job Listings");
            System.out.println("6. View Applications for a Job");
            System.out.println("7. Show Job Listings by Salary Range");
            System.out.println("8. View All Companies");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            
            choice = scanner.nextInt();
            scanner.nextLine();  
            
            switch (choice) {
                case 1:
                    registerApplicant();
                    break;
                case 2:
                	insertCompanyDetails(); 
                    break;
                case 3:
                    postJob();
                    break;
                case 4:
                    applyForJob();
                    break;
                case 5:
                    viewJobListings();
                    break;
                case 6:
                    viewApplicationsForJob();
                    break;
                case 7:
                	showJobListingsBySalaryRange();
                    break;
                    
                case 8 :
                	getAllCompanies();  
                    break;
                	
                case 9:
                    System.out.println("Exiting the system...");
                    System.out.println("Thank You");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 9);
    }
    
    
    
    
    // Register an applicant
    private static void registerApplicant() {
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();
        
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Enter Phone Number: ");
        String phone = scanner.nextLine();

        System.out.print("Enter Resume (URL or short text): ");
        String resume = scanner.nextLine();  // <-- new line to collect resume
        
        Applicant applicant = new Applicant();
        applicant.setFirstName(firstName);
        applicant.setLastName(lastName);
        applicant.setEmail(email);
        applicant.setPhone(phone);
        applicant.setResume(resume);  
        
        try {
            ApplicantDAO applicantDAO = new ApplicantDAOImpl(conn);
            applicantDAO.insertApplicant(applicant);
            System.out.println("Applicant registered successfully.");
        } catch (InvalidEmailFormatException | SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    
    
    private static void insertCompanyDetails() {
        System.out.print("Enter Company Name: ");
        String companyName = scanner.nextLine();
        
        System.out.print("Enter Company Location: ");
        String location = scanner.nextLine();

        Company company = new Company(0, companyName, location);
        company.setCompanyName(companyName);
        company.setLocation(location);

        try {
            
            CompanyDAO companyDAO = new CompanyDAOImpl(conn);
            companyDAO.insertCompany(company);
            System.out.println("Company inserted successfully.");
        } catch (SQLException e) {
            System.out.println("Error inserting company: " + e.getMessage());
        }
    }

    
    
    
    
    
    
    // Post a job (For companies)
    private static void postJob() {
        System.out.print("Enter Company ID: ");
        int companyID = scanner.nextInt();
        scanner.nextLine();  
        System.out.print("Enter Job Title: ");
        String jobTitle = scanner.nextLine();
        System.out.print("Enter Job Description: ");
        String jobDescription = scanner.nextLine();
        System.out.print("Enter Job Location: ");
        String jobLocation = scanner.nextLine();
        System.out.print("Enter Salary: ");
        double salary = scanner.nextDouble();
        scanner.nextLine(); 
        System.out.print("Enter Job Type (e.g., Full-time, Part-time): ");
        String jobType = scanner.nextLine();
        
        JobListing jobListing = new JobListing(companyID,jobTitle,jobDescription,jobLocation,salary,jobType);
        jobListing.setCompanyID(companyID);
        jobListing.setJobTitle(jobTitle);
        jobListing.setJobDescription(jobDescription);
        jobListing.setJobLocation(jobLocation);
        jobListing.setSalary(salary);
        jobListing.setJobType(jobType);
        
        try {
            JobListingDAO jobListingDAO = new JobListingDAOImpl(conn);
            jobListingDAO.insertJobListing(jobListing);
            System.out.println("Job posted successfully.");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    
    
    
    
    // Apply for a job
    private static void applyForJob() {
        System.out.print("Enter Applicant ID: ");
        int applicantID = scanner.nextInt();
        System.out.print("Enter Job ID: ");
        int jobID = scanner.nextInt();
        scanner.nextLine();  // Consume the newline
        System.out.print("Enter Cover Letter: ");
        String coverLetter = scanner.nextLine();

        
        JobApplication jobApplication = new JobApplication(0, jobID, applicantID, coverLetter, null);
        
        jobApplication.setApplicantID(applicantID);
        jobApplication.setJobID(jobID);
        jobApplication.setCoverLetter(coverLetter);

        try {
            
            JobApplicationDAO jobApplicationDAO = new JobApplicationDAOImpl(conn);
            jobApplicationDAO.insertJobApplication(jobApplication);
            System.out.println("Application submitted successfully.");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    
    
    
    
    
    
    // View job listings
    private static void viewJobListings() {
        try {
            JobListingDAO jobListingDAO = new JobListingDAOImpl(conn);
            List<JobListing> jobListings = jobListingDAO.getJobListings();
            
            if (jobListings.isEmpty()) {
                System.out.println("No job listings found.");
            } else {
                for (JobListing job : jobListings) {
                    System.out.println("Job Title: " + job.getJobTitle());
                    System.out.println("Company ID: " + job.getCompanyID());
                    System.out.println("Location: " + job.getJobLocation());
                    System.out.println("Salary: " + job.getSalary());
                    System.out.println("Job Type: " + job.getJobType());
                    System.out.println("------------------------------------");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving job listings: " + e.getMessage());
        }
    }
    
    
    
    
    
    
    
    
    
    // View applications for a specific job
    private static void viewApplicationsForJob() {
        System.out.print("Enter Job ID: ");
        int jobID = scanner.nextInt();
        
        try {
            JobApplicationDAO jobApplicationDAO = new JobApplicationDAOImpl(conn);
            List<JobApplication> jobApplications = jobApplicationDAO.getJobApplicationsForJob(jobID);
            
            if (jobApplications.isEmpty()) {
                System.out.println("No applications found for this job.");
            } else {
                for (JobApplication application : jobApplications) {
                    System.out.println("Applicant ID: " + application.getApplicantID());
                    System.out.println("Cover Letter: " + application.getCoverLetter());
                    System.out.println("Application Date: " + application.getApplicationDate());
                    System.out.println("------------------------------------");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving job applications: " + e.getMessage());
        }
    }
    
    
    
    
    
    
 // Method to get all companies from the database
    private static void getAllCompanies() {
        try {
            
            CompanyDAO companyDAO = new CompanyDAOImpl(conn);
            List<Company> companies = companyDAO.getAllCompanies();

            if (companies.isEmpty()) {
                System.out.println("No companies found.");
            } else {
                System.out.println("List of Companies:");
                for (Company company : companies) {
                    System.out.println("Company ID: " + company.getCompanyID());
                    System.out.println("Company Name: " + company.getCompanyName());
                    System.out.println("Location: " + company.getLocation()); 
                    System.out.println("-------------------------------------");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving companies: " + e.getMessage());
        }
    }

    
    
    
    
    
    
    
    
    
    private static void showJobListingsBySalaryRange() throws SQLException {
        System.out.print("Enter minimum salary: ");
        double minSalary = scanner.nextDouble();
        System.out.print("Enter maximum salary: ");
        double maxSalary = scanner.nextDouble();

        try {
            // Initialize DatabaseManager and fetch job listings by salary range
            DatabaseManager dbManager = new DatabaseManager(conn);
            List<JobListing> jobListings = dbManager.getJobListingsBySalaryRange(minSalary, maxSalary);

            if (jobListings.isEmpty()) {
                System.out.println("No jobs found within the specified salary range.");
            } else {
                System.out.println("Job Listings within Salary Range (" + minSalary + " to " + maxSalary + "):");
                for (JobListing job : jobListings) {
                    System.out.println("Job Title: " + job.getJobTitle());
                    System.out.println("Job Description: " + job.getJobDescription());
                    System.out.println("Location: " + job.getJobLocation());
                    System.out.println("Salary: " + job.getSalary());
                    System.out.println("Job Type: " + job.getJobType());
                    System.out.println("---------------------------");
                }
            }
        } catch (DatabaseConnectionException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
