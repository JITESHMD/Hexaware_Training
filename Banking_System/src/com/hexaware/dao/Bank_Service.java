package com.hexaware.dao;

import com.hexaware.entity.Customer;
import com.hexaware.entity.Account;

import java.util.List;

public interface Bank_Service {
    Account createAccount(Customer customer, String accType, float balance);
    List<Account> listAccounts();
    void calculateInterest();
}