package com.hexaware.entity;

import java.sql.Date;

public class Payment {
    private int paymentId;
    private int leaseId;
    private double amount;
    private Date paymentDate;

    public Payment() {
    }

    public Payment(int paymentId, int leaseId, double amount, Date paymentDate) {
        this.paymentId = paymentId;
        this.leaseId = leaseId;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getLeaseId() {
        return leaseId;
    }

    public void setLeaseId(int leaseId) {
        this.leaseId = leaseId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Override
    public String toString() {
        return "Payment [ID=" + paymentId + ", LeaseID=" + leaseId + ", Amount=" + amount +
               ", PaymentDate=" + paymentDate + "]";
    }
}
