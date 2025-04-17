package com.hexaware.entity;

import java.sql.Date;

public class Lease {
    private int leaseId;
    private int customerId;
    private int carId;
    private Date startDate;
    private Date endDate;
    private String leaseType; // "Daily" or "Monthly"
    private double totalCost;
    private boolean isActive;

    public Lease(int leaseId, int customerId, int carId, Date startDate, Date endDate, String leaseType, double totalCost, boolean isActive) {
        this.leaseId = leaseId;
        this.customerId = customerId;
        this.carId = carId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.leaseType = leaseType;
        this.totalCost = totalCost;
        this.isActive = isActive;
    }

    public int getLeaseId() {
        return leaseId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getCarId() {
        return carId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getLeaseType() {
        return leaseType;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public boolean isActive() {
        return isActive;
    }

    @Override
    public String toString() {
        return "Lease {" +
                "leaseId=" + leaseId +
                ", customerId=" + customerId +
                ", carId=" + carId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", leaseType='" + leaseType + '\'' +
                ", totalCost=" + totalCost +
                ", isActive=" + isActive +
                '}';
    }

	

	public Object getPaymentMode() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getPaymentMode1() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
