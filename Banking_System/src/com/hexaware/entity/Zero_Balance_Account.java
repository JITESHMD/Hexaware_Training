package com.hexaware.entity;

public class Zero_Balance_Account extends Account {

    public Zero_Balance_Account(float balance, Customer customer) {
        super(balance, customer);
    }

    @Override
    public void withdraw(float amount) throws Exception {
        if (getBalance() >= amount) {
            setBalance(getBalance() - amount);
        } else {
            throw new Exception("❌ Cannot withdraw. Balance is insufficient.");
        }
    }
}