package com.hexaware.entity;

public class Savings_Account extends Account {
    private float interestRate;

    public Savings_Account(float balance, Customer customer, float interestRate) {
        super(balance, customer);
        this.interestRate = interestRate;
    }

    public void calculateInterest() {
        float interest = getBalance() * (interestRate / 100);
        setBalance(getBalance() + interest);
    }

    @Override
    public void withdraw(float amount) throws Exception {
        if (getBalance() >= amount) {
            setBalance(getBalance() - amount);
        } else {
            throw new Exception("❌ Insufficient balance.");
        }
    }
}