package com.hexaware.entity;

public class Car {
    private int carId;
    private String model;
    private String brand;
    private double dailyRate;
    private double monthlyRate;
    private boolean isAvailable;

    public Car(int carId, String model, String brand, boolean isAvailable) {
        
    }

    public Car(int carId, String model, String brand, double dailyRate, double monthlyRate, boolean isAvailable) {
        this.carId = carId;
        this.model = model;
        this.brand = brand;
        this.dailyRate = dailyRate;
        this.monthlyRate = monthlyRate;
        this.isAvailable = isAvailable;
    }


    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(double dailyRate) {
        this.dailyRate = dailyRate;
    }

    public double getMonthlyRate() {
        return monthlyRate;
    }

    public void setMonthlyRate(double monthlyRate) {
        this.monthlyRate = monthlyRate;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Car [ID=" + carId + ", Model=" + model + ", Brand=" + brand +
               ", DailyRate=" + dailyRate + ", MonthlyRate=" + monthlyRate +
               ", Available=" + isAvailable + "]";
    }
}
