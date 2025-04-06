package com.hexaware.service;

import com.hexaware.entity.Account;
import java.util.List;

public class Customer_Service_Provider_Implementation {

    // This protected list allows extending classes to access accountList directly
    protected List<Account> accountList;

    // Constructor initializes the account list
    public Customer_Service_Provider_Implementation(List<Account> accountList) {
        this.accountList = accountList;
    }
}