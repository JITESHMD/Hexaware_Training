package com.hexaware.dao;

import com.hexaware.entity.*;
import com.hexaware.service.Bank_Service_Provider;
import com.hexaware.service.Customer_Service_Provider_Implementation;

import java.util.ArrayList;
import java.util.List;

public class Bank_Service_Implementation extends Customer_Service_Provider_Implementation implements Bank_Service_Provider {

    public Bank_Service_Implementation() {
        super(new ArrayList<>());
    }

    @Override
    public Account createAccount(Customer customer, String accountType, float balance) {
        Account acc = null;

        switch (accountType.toLowerCase()) {
            case "savings":
                acc = new Savings_Account(balance, customer, 4.0f);
                break;
            case "current":
                acc = new Current_Account(balance, customer);
                break;
            case "zerobalance":
                acc = new Zero_Balance_Account(balance, customer);
                break;
            default:
                System.out.println("❌ Invalid account type.");
                return null;
        }

        accountList.add(acc);
        return acc;
    }

    @Override
    public float deposit(long accNo, float amount) {
        for (Account acc : accountList) {
            if (acc.getAccountNumber() == accNo) {
                acc.setBalance(acc.getBalance() + amount);
                return acc.getBalance();
            }
        }
        return 0;
    }

    @Override
    public float withdraw(long accNo, float amount) throws Exception {
        for (Account acc : accountList) {
            if (acc.getAccountNumber() == accNo) {
                acc.withdraw(amount);
                return acc.getBalance();
            }
        }
        throw new Exception("Account not found");
    }

    @Override
    public boolean transfer(long fromAcc, long toAcc, float amount) throws Exception {
        Account sender = null, receiver = null;

        for (Account acc : accountList) {
            if (acc.getAccountNumber() == fromAcc) sender = acc;
            else if (acc.getAccountNumber() == toAcc) receiver = acc;
        }

        if (sender != null && receiver != null) {
            sender.withdraw(amount);
            receiver.setBalance(receiver.getBalance() + amount);
            return true;
        }

        return false;
    }

    @Override
    public float getAccountBalance(long accNo) {
        for (Account acc : accountList) {
            if (acc.getAccountNumber() == accNo) {
                return acc.getBalance();
            }
        }
        return 0;
    }

    @Override
    public Account getAccountDetails(long accNo) {
        for (Account acc : accountList) {
            if (acc.getAccountNumber() == accNo) {
                return acc;
            }
        }
        return null;
    }

    @Override
    public List<Account> listAccounts() {
        return accountList;
    }

    @Override
    public void calculateInterest() {
        for (Account acc : accountList) {
            if (acc instanceof Savings_Account) {
                ((Savings_Account) acc).calculateInterest();
            }
        }
    }
}