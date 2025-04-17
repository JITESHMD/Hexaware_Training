package com.hexaware.dao;

import com.hexaware.entity.*;
import com.hexaware.myExceptions.*;
import com.hexaware.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public  class ICarLeaseRepositoryImplementation implements ICarLeaseRepository {

    private Connection conn = DBConnection.getConnection();

    // ----- Car Management -----

    @Override
    public void addCar(Car car) {
        String query = "INSERT INTO cars (model, brand, daily_rate, monthly_rate, available) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, car.getModel());
            pstmt.setString(2, car.getBrand());
            pstmt.setDouble(3, car.getDailyRate());
            pstmt.setDouble(4, car.getMonthlyRate());
            pstmt.setBoolean(5, car.isAvailable());
            pstmt.executeUpdate();
            System.out.println("Car added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void removeCar(int carId) {
        String query = "DELETE FROM cars WHERE car_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, carId);
            int rows = pstmt.executeUpdate();
            if (rows == 0) {
                throw new CarNotFoundException("Car with ID " + carId + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Car> listAvailableCars() {
        List<Car> cars = new ArrayList<>();
        String query = "SELECT * FROM cars WHERE available = true";
        try (PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                cars.add(new Car(
                    rs.getInt("car_id"),
                    rs.getString("model"),
                    rs.getString("brand"),
                    rs.getDouble("daily_rate"),
                    rs.getDouble("monthly_rate"),
                    rs.getBoolean("available")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }


    @Override
    public List<Car> listRentedCars() {
        List<Car> cars = new ArrayList<>();
        String query = "SELECT * FROM cars WHERE available = false";
        try (PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                cars.add(new Car(
                    rs.getInt("car_id"),
                    rs.getString("model"),
                    rs.getString("brand"),
                    rs.getDouble("daily_rate"),
                    rs.getDouble("monthly_rate"),
                    rs.getBoolean("available")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }


    @Override
    public Car findCarById(int carId) {
        String query = "SELECT * FROM cars WHERE car_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, carId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Car(
                    rs.getInt("car_id"),
                    rs.getString("model"),
                    rs.getString("brand"),
                    rs.getDouble("daily_rate"),
                    rs.getDouble("monthly_rate"),
                    rs.getBoolean("available")
                );
            } else {
                throw new CarNotFoundException("Car ID " + carId + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    // ----- Customer Management -----

    @Override
    public void addCustomer(Customers customer) throws ValidationException{
        //  validation
    	 StringBuilder errorMsg = new StringBuilder();

    	    if (!customer.getEmail().matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$")) {
    	        errorMsg.append("Invalid email format. ");
    	    }

    	    if (!customer.getPhone().matches("\\d{10}")) {
    	        errorMsg.append("Phone number must be exactly 10 digits.");
    	    }

    	    if (errorMsg.length() > 0) {
    	        throw new ValidationException(errorMsg.toString());
    	    }


        String query = "INSERT INTO customers (customer_id, first_name, last_name, phone, email, model) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, customer.getCustomerId());
            pstmt.setString(2, customer.getFirstName());
            pstmt.setString(3, customer.getLastName());
            pstmt.setString(4, customer.getPhone());
            pstmt.setString(5, customer.getEmail());
            pstmt.setString(6, customer.getModel()); 
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void removeCustomer(int customerId) {
        String query = "DELETE FROM customers WHERE customer_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, customerId);
            int rows = pstmt.executeUpdate();
            if (rows == 0) {
                throw new CustomerNotFoundException("Customer with ID " + customerId + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Customers> listCustomers() {
        List<Customers> customers = new ArrayList<>();
        String query = "SELECT * FROM customers";
        try (PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                customers.add(new Customers(
                    rs.getInt("customer_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("phone"),
                    rs.getString("email"),
                    rs.getString("model")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }


    @Override
    public Customers findCustomerById(int customerId) {
        String query = "SELECT * FROM customers WHERE customer_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, customerId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Customers(
                    rs.getInt("customer_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("phone"),
                    rs.getString("email"),
                    rs.getString("model")
                );
            } else {
                throw new CustomerNotFoundException("Customer with ID " + customerId + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    // ----- Lease Management -----
   


//    public Lease createLease(int customerId, int carId, Date startDate, Date endDate) {
//        String getRatesQuery = "SELECT daily_rate, monthly_rate FROM cars WHERE car_id = ?";
//        String insertLeaseQuery = "INSERT INTO leases (customer_id, car_id, start_date, end_date, lease_type, total_cost, is_active) VALUES (?, ?, ?, ?, ?, ?, true)";
//
//        try (PreparedStatement rateStmt = conn.prepareStatement(getRatesQuery)) {
//            rateStmt.setInt(1, carId);
//            ResultSet rateRs = rateStmt.executeQuery();
//
//            if (rateRs.next()) {
//                double dailyRate = rateRs.getDouble("daily_rate");
//                double monthlyRate = rateRs.getDouble("monthly_rate");
//
//                long durationInDays = (endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24);
//                double totalCost;
//                String leaseType;
//
//                if (durationInDays <= 20) {
//                    leaseType = "Daily";
//                    totalCost = durationInDays * dailyRate;
//                } else {
//                    leaseType = "Monthly";
//                    double months = durationInDays / 30.0;
//                    totalCost = months * monthlyRate;
//                }
//
//                try (PreparedStatement insertStmt = conn.prepareStatement(insertLeaseQuery, Statement.RETURN_GENERATED_KEYS)) {
//                    insertStmt.setInt(1, customerId);
//                    insertStmt.setInt(2, carId);
//                    insertStmt.setDate(3, startDate);
//                    insertStmt.setDate(4, endDate);
//                    insertStmt.setString(5, leaseType);
//                    insertStmt.setDouble(6, totalCost);
//                    insertStmt.executeUpdate();
//
//                    ResultSet keys = insertStmt.getGeneratedKeys();
//                    if (keys.next()) {
//                        int leaseId = keys.getInt(1);
//                        updateCarAvailability(carId, false); // make car unavailable
//                        return new Lease(leaseId, customerId, carId, startDate, endDate, leaseType, totalCost, true);
//                    }
//                }
//            } else {
//                System.out.println("Car with ID " + carId + " not found.");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
    
    public Lease createLease(int customerId, int carId, Date startDate, Date endDate) {
        String getRatesQuery = "SELECT daily_rate, monthly_rate FROM cars WHERE car_id = ?";
        String insertLeaseQuery = "INSERT INTO leases (customer_id, car_id, start_date, end_date, lease_type, total_cost, is_active) VALUES (?, ?, ?, ?, ?, ?, true)";

        try (PreparedStatement rateStmt = conn.prepareStatement(getRatesQuery)) {
            rateStmt.setInt(1, carId);
            ResultSet rateRs = rateStmt.executeQuery();

            if (rateRs.next()) {
                double dailyRate = rateRs.getDouble("daily_rate");
                double monthlyRate = rateRs.getDouble("monthly_rate");

                long durationInDays = (endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24);
                double totalCost;
                String leaseType;

                if (durationInDays <= 20) {
                    leaseType = "Daily";
                    totalCost = durationInDays * dailyRate;
                } else {
                    leaseType = "Monthly";
                    double months = durationInDays / 30.0;
                    totalCost = months * monthlyRate;
                }

                try (PreparedStatement insertStmt = conn.prepareStatement(insertLeaseQuery, Statement.RETURN_GENERATED_KEYS)) {
                    insertStmt.setInt(1, customerId);
                    insertStmt.setInt(2, carId);
                    insertStmt.setDate(3, startDate);
                    insertStmt.setDate(4, endDate);
                    insertStmt.setString(5, leaseType);
                    insertStmt.setDouble(6, totalCost);
                    insertStmt.executeUpdate();

                    ResultSet keys = insertStmt.getGeneratedKeys();
                    if (keys.next()) {
                        int leaseId = keys.getInt(1);
                        updateCarAvailability(carId, false); // make car unavailable
                        return new Lease(leaseId, customerId, carId, startDate, endDate, leaseType, totalCost, true);
                    }
                }
            } else {
                System.out.println("Car with ID " + carId + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    
    
    public void updateCarAvailability(int carId, boolean availability) {
        String query = "UPDATE cars SET available = ? WHERE car_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setBoolean(1, availability);
            pstmt.setInt(2, carId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Car availability updated successfully.");
            } else {
                System.out.println("Car with ID " + carId + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    
    




//    private void updateCarAvailability(int carId, boolean status) throws SQLException {
//        String query = "UPDATE cars SET available = ? WHERE car_id = ?";
//        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
//            pstmt.setBoolean(1, status);
//            pstmt.setInt(2, carId);
//            pstmt.executeUpdate();
//        }
//    }
    
    

    
    @Override
    public void returnCar(int leaseId) {
        String query = "UPDATE leases SET is_active = false WHERE lease_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, leaseId);
            int rows = pstmt.executeUpdate();

            if (rows == 0) {
                throw new LeaseNotFoundException("Lease with ID " + leaseId + " not found.");
            }

            // Make the car available again
            int carId = getCarIdByLeaseId(leaseId);
            updateCarAvailability(carId, true);

            System.out.println("Car returned successfully and lease marked as inactive.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    

    private int getCarIdByLeaseId(int leaseId) throws SQLException {
        String query = "SELECT car_id FROM leases WHERE lease_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, leaseId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("car_id");
            } else {
                throw new LeaseNotFoundException("Lease with ID " + leaseId + " not found.");
            }
        }
    }

    
    
    @Override
    public List<Lease> listActiveLeases() {
        List<Lease> leases = new ArrayList<>();
        String query = "SELECT * FROM leases WHERE is_active = true";
        try (PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                leases.add(new Lease(rs.getInt("lease_id"), rs.getInt("customer_id"), rs.getInt("car_id"),
                        rs.getDate("start_date"), rs.getDate("end_date"),
                        rs.getString("lease_type"), rs.getDouble("total_cost"), rs.getBoolean("is_active")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return leases;
    }
    
    
    

    @Override
    public List<Lease> listLeaseHistory() {
        List<Lease> leases = new ArrayList<>();
        String query = "SELECT * FROM leases";
        try (PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                leases.add(new Lease(rs.getInt("lease_id"), rs.getInt("customer_id"), rs.getInt("car_id"),
                        rs.getDate("start_date"), rs.getDate("end_date"),
                        rs.getString("lease_type"), rs.getDouble("total_cost"), rs.getBoolean("is_active")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return leases;
    }

    // ----- Payment Handling -----
    
    public void recordPayment(Lease lease, double amount) {
        // Check if the lease is null before proceeding
        if (lease == null) {
            System.out.println("Error: Lease is null. Cannot process payment.");
            return; // exit the method early if lease is null
        }

        String query = "INSERT INTO payments (lease_id, amount_paid, payment_date) VALUES (?, ?, CURDATE())";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, lease.getLeaseId());  // Lease ID from the Lease object
            pstmt.setDouble(2, amount);  // Payment amount
            pstmt.executeUpdate();  // Execute the query to insert the payment record
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    
    
    
    
    public List<Payment> getPaymentsByLeaseId(int leaseId) {
        List<Payment> payments = new ArrayList<>();
        String query = "SELECT * FROM payments WHERE lease_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, leaseId);  // Set the lease ID parameter
            ResultSet rs = pstmt.executeQuery();  // Execute the query

            while (rs.next()) {
                // Create and add Payment objects to the list
                payments.add(new Payment(
                    rs.getInt("payment_id"), // payment ID
                    rs.getInt("lease_id"),   // lease ID
                    rs.getDouble("amount_paid"),  // payment amount
                    rs.getDate("payment_date") // payment date
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return payments;  // Return the list of payments
    }

    
    
    
    
    

    public double calculateTotalRevenue() {
        String query = "SELECT SUM(amount_paid) AS total FROM payments";
        double totalRevenue = 0;

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            if (rs.next()) {
                totalRevenue = rs.getDouble("total");  // Get the total revenue
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalRevenue;  // Return the total revenue
    }

    
    

    public Lease findLeaseById(int leaseId) {
        String query = "SELECT * FROM leases WHERE lease_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, leaseId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Lease(
                    rs.getInt("lease_id"),
                    rs.getInt("customer_id"),
                    rs.getInt("car_id"),
                    rs.getDate("start_date"),
                    rs.getDate("end_date"),
                    rs.getString("lease_type"),
                    rs.getDouble("total_cost"),
                    rs.getBoolean("is_active")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
	
	

	@Override
	public List<Payment> getPaymentHistoryByCustomerId(int customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getTotalRevenue() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public Lease createLease(int customerId, int carId, Date startDate, Date endDate, String leaseType)
			throws CarNotFoundException, CustomerNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}


	
	


	
	


	
	
}
