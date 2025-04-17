package Junit;

import com.hexaware.dao.ICarLeaseRepositoryImplementation;
import com.hexaware.entity.Car;
import com.hexaware.entity.Lease;
import com.hexaware.myExceptions.CarNotFoundException;
import com.hexaware.myExceptions.CustomerNotFoundException;
import com.hexaware.myExceptions.LeaseNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class CarRentalSystemTest {

    private ICarLeaseRepositoryImplementation repo;

    @BeforeEach
    void setUp() {
        repo = new ICarLeaseRepositoryImplementation(); // Initialize the repository
    }

 // Test case to test car creation
    @Test
    void testCarCreatedSuccessfully() {
        Car car = new Car(0, "Tesla Model 3", "Tesla", 100, 2000, true);

        try {
            // Try to add the car; we assume it works if no exception is thrown
            repo.addCar(car);
            assertTrue(true, "Car should be added successfully");
        } catch (Exception e) {
            fail("Exception occurred while adding the car: " + e.getMessage());
        }
    }


    // Test case to test lease creation
    @Test
    void testLeaseCreatedSuccessfully() {
        try {
            // Assuming customerId and carId exist in the database
            int customerId = 1;
            int carId = 1;
            Date startDate = Date.valueOf("2025-05-01");
            Date endDate = Date.valueOf("2025-06-01");

            Lease lease = repo.createLease(customerId, carId, startDate, endDate);

            assertNotNull(lease, "Lease should be created successfully");
            assertEquals(customerId, lease.getCustomerId(), "Customer ID should match");
            assertEquals(carId, lease.getCarId(), "Car ID should match");
        } catch (CarNotFoundException | CustomerNotFoundException e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    // Test case to test lease retrieval
    @Test
    void testLeaseRetrievedSuccessfully() {
        try {
            int leaseId = 1; // Assuming this lease ID exists in the database
            Lease lease = repo.findLeaseById(leaseId);

            assertNotNull(lease, "Lease should be retrieved successfully");
            assertEquals(leaseId, lease.getLeaseId(), "Lease ID should match");
        } catch (LeaseNotFoundException e) {
            fail("Lease not found: " + e.getMessage());
        }
    }

    // Test case to test exception handling (CustomerNotFoundException)
    @Test
    void testCustomerNotFoundException() {
        int invalidCustomerId = -1; // Assuming this ID doesn't exist in the database
        assertThrows(CustomerNotFoundException.class, () -> repo.findCustomerById(invalidCustomerId), 
                "CustomerNotFoundException should be thrown");
    }

    // Test case to test exception handling (CarNotFoundException)
    @Test
    void testCarNotFoundException() {
        int invalidCarId = -1; // Assuming this ID doesn't exist in the database
        assertThrows(CarNotFoundException.class, () -> repo.findCarById(invalidCarId), 
                "CarNotFoundException should be thrown");
    }

    // Test case to test exception handling (LeaseNotFoundException)
    @Test
    void testLeaseNotFoundReturnsNull() {
        Lease lease = repo.findLeaseById(-999); // use an ID that doesn't exist
        assertNull(lease, "Expected null when lease ID is not found.");
    }

}
