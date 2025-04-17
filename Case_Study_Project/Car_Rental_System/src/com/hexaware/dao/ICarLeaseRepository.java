package com.hexaware.dao;

import java.sql.Date;
import java.util.List;

import com.hexaware.entity.Car;
import com.hexaware.entity.Customers;
import com.hexaware.entity.Lease;
import com.hexaware.entity.Payment;
import com.hexaware.myExceptions.CarNotFoundException;
import com.hexaware.myExceptions.CustomerNotFoundException;
import com.hexaware.myExceptions.LeaseNotFoundException;
import com.hexaware.myExceptions.ValidationException;

public interface ICarLeaseRepository {

    // Car Management
    void addCar(Car car);
    void removeCar(int carId) throws CarNotFoundException;
    List<Car> listAvailableCars();
    List<Car> listRentedCars();
    Car findCarById(int carId) throws CarNotFoundException;

    // Customer Management
    void addCustomer(Customers customer) throws ValidationException;
    void removeCustomer(int customerId) throws CustomerNotFoundException;
    List<Customers> listCustomers();
    Customers findCustomerById(int customerId) throws CustomerNotFoundException;

    // Lease Management
    Lease createLease(int customerId, int carId, Date startDate, Date endDate, String leaseType)
        throws CarNotFoundException, CustomerNotFoundException;
    void returnCar(int leaseId) throws LeaseNotFoundException;


    List<Lease> listActiveLeases();
    List<Lease> listLeaseHistory();

    // Payment Handling
    void recordPayment(Lease lease, double amount);
    
    List<Payment> getPaymentHistoryByCustomerId(int customerId);
    double getTotalRevenue();
    
}
