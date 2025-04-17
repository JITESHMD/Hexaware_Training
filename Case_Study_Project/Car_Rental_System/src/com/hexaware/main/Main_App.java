package com.hexaware.main;

import com.hexaware.dao.ICarLeaseRepositoryImplementation;
import com.hexaware.entity.*;
import com.hexaware.myExceptions.*;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

@SuppressWarnings("unused")
public class Main_App {

    @SuppressWarnings("resource")
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ICarLeaseRepositoryImplementation repo = new ICarLeaseRepositoryImplementation();

        System.out.println("Welcome to Car Rental System");
        System.out.print("Are you an Admin or Customer? (admin/customer): ");
        String role = sc.nextLine().trim().toLowerCase();

        if (!role.equals("admin") && !role.equals("customer")) {
            System.out.println("Invalid role entered. Exiting...");
            return;
        }

        while (true) {
            System.out.println("\n===== Car Rental System Menu =====");

            // show menu based on role
            if (role.equals("admin")) {
                System.out.println("1. Add Car");
                System.out.println("2. List Available Cars");
                System.out.println("3. List Rented Cars");
                System.out.println("4. Find Car by ID");
                System.out.println("5. Add Customer");
                System.out.println("6. Remove Customer");
                System.out.println("7. List Customers");
                System.out.println("8. Find Customer by ID");
                System.out.println("11. List Active Leases");
                System.out.println("12. List Lease History");
                System.out.println("13. Record Payment");
                System.out.println("14. View Payment History by Lease ID");
                System.out.println("15. View Total Revenue");
                System.out.println("16. Find Lease by ID");
            } else if (role.equals("customer")) {
                System.out.println("2. List Available Cars");
                System.out.println("4. Find Car by ID");
                System.out.println("9. Create Lease");
                System.out.println("10. Return Car");
               
            }

            System.out.println("17. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            try {
                switch (choice) {
                    // --- admin functionalities ---
                    case 1:
                        if (role.equals("admin")) {
                            System.out.print("Enter Car Model: ");
                            String model = sc.nextLine();

                            System.out.print("Enter Car Brand: ");
                            String brand = sc.nextLine();

                            System.out.print("Enter Daily Rate: ");
                            double dailyRate = sc.nextDouble();

                            System.out.print("Enter Monthly Rate: ");
                            double monthlyRate = sc.nextDouble();
                            sc.nextLine();

                            System.out.print("Is the car available? (true/false): ");
                            boolean isAvailable = sc.nextBoolean();

                            Car car = new Car(0, model, brand, dailyRate, monthlyRate, isAvailable);
                            repo.addCar(car);
                        }
                        break;

                    case 2:
                        List<Car> availableCars = repo.listAvailableCars();
                        availableCars.forEach(System.out::println);
                        break;

                    case 3:
                        if (role.equals("admin")) {
                            List<Car> rentedCars = repo.listRentedCars();
                            rentedCars.forEach(System.out::println);
                        }
                        break;

                    case 4:
                        System.out.print("Enter Car ID: ");
                        int searchCarId = sc.nextInt();
                        Car foundCar = repo.findCarById(searchCarId);
                        System.out.println(foundCar);
                        break;

                    case 5:
                        if (role.equals("admin")) {
                            System.out.print("Enter Customer ID: ");
                            int custId = sc.nextInt();
                            sc.nextLine();
                            System.out.print("Enter First Name: ");
                            String fName = sc.nextLine();
                            System.out.print("Enter Last Name: ");
                            String lName = sc.nextLine();
                            System.out.print("Enter Contact Number: ");
                            String contact = sc.nextLine();
                            System.out.print("Enter Email: ");
                            String email = sc.nextLine();
                            System.out.print("Enter Model: ");
                            String model1 = sc.nextLine();

                            Customers cust = new Customers(custId, fName, lName, email, contact, model1);
                            repo.addCustomer(cust);
                        }
                        break;

                    case 6:
                        if (role.equals("admin")) {
                            System.out.print("Enter Customer ID to remove: ");
                            int removeId = sc.nextInt();
                            repo.removeCustomer(removeId);
                            System.out.println("Customer removed successfully!");
                        }
                        break;

                    case 7:
                        if (role.equals("admin")) {
                            List<Customers> customers = repo.listCustomers();
                            customers.forEach(System.out::println);
                        }
                        break;

                    case 8:
                        if (role.equals("admin")) {
                            System.out.print("Enter Customer ID: ");
                            int findId = sc.nextInt();
                            Customers found = repo.findCustomerById(findId);
                            System.out.println(found);
                        }
                        break;

                    // --- customer functionalities ---
                    case 9:
                        if (role.equals("customer")) {
                            System.out.print("Enter Customer ID: ");
                            int customerId = sc.nextInt();

                            System.out.print("Enter Car ID: ");
                            int carId = sc.nextInt();
                            sc.nextLine();

                            System.out.print("Enter Start Date (yyyy-mm-dd): ");
                            Date startDate = Date.valueOf(sc.nextLine());

                            System.out.print("Enter End Date (yyyy-mm-dd): ");
                            Date endDate = Date.valueOf(sc.nextLine());

                            Lease lease = repo.createLease(customerId, carId, startDate, endDate);
                            if (lease != null) {
                                System.out.println("Lease successfully created!");
                                System.out.println(lease.toString());
                            } else {
                                System.out.println("Failed to create lease.");
                            }
                        }
                        break;

                    case 10:
                        if (role.equals("customer")) {
                            System.out.print("Enter Lease ID to return: ");
                            int leaseId = sc.nextInt();
                            repo.returnCar(leaseId);
                            System.out.println("Car returned successfully.");
                        }
                        break;

                    case 11:
                        if (role.equals("admin")) {
                            List<Lease> activeLeases = repo.listActiveLeases();
                            activeLeases.forEach(System.out::println);
                        }
                        break;

                    case 12:
                        if (role.equals("admin")) {
                            List<Lease> leaseHistory = repo.listLeaseHistory();
                            leaseHistory.forEach(System.out::println);
                        }
                        break;

                    case 13:
                        if (role.equals("customer")) {
                            System.out.print("Enter Lease ID to record payment: ");
                            int leaseId = sc.nextInt();
                            Lease lease = repo.findLeaseById(leaseId);

                            if (lease != null) {
                                System.out.print("Enter payment amount: ");
                                double amount = sc.nextDouble();
                                repo.recordPayment(lease, amount);
                                System.out.println("Payment Added Successfully!");
                            } else {
                                System.out.println("Lease not found.");
                            }
                        }
                        break;

                    case 14:
                        if (role.equals("admin")) {
                            System.out.print("Enter Lease ID to view payment history: ");
                            int leaseIdForPayments = sc.nextInt();
                            List<Payment> paymentHistory = repo.getPaymentsByLeaseId(leaseIdForPayments);

                            if (paymentHistory.isEmpty()) {
                                System.out.println("No payment history found for Lease ID: " + leaseIdForPayments);
                            } else {
                                System.out.println("Payment History:");
                                for (Payment payment : paymentHistory) {
                                    System.out.println(payment);
                                }
                            }
                        }
                        break;

                    case 15:
                        if (role.equals("admin")) {
                            double totalRevenue = repo.calculateTotalRevenue();
                            System.out.println("Total Revenue from all payments: ₹" + totalRevenue);
                        }
                        break;

                    case 16:
                        System.out.print("Enter Lease ID to find: ");
                        int findLeaseId = sc.nextInt();
                        Lease foundLease = repo.findLeaseById(findLeaseId);

                        if (foundLease != null) {
                            System.out.println("Lease Details:");
                            System.out.println(foundLease);
                        } else {
                            System.out.println("Lease not found.");
                        }
                        break;

                    case 17:
                        System.out.println("Exiting...");
                        sc.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid option.");
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
