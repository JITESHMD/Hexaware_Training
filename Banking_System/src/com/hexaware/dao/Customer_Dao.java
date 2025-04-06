package com.hexaware.dao;


import com.hexaware.entity.Customer;
import java.util.List;

public interface Customer_Dao {
    void insertCustomer(Customer customer) throws Exception;
    List<Customer> getAllCustomers() throws Exception;
}