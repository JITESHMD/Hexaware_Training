package com.hexaware.entity;

public class Customer {
    private long customerId;
    private String firstName;
    private String lastName;
    private String DOB;
    private String email;
    private String phone;
    private String address;

    // ✅ Full-argument constructor (the one needed to fix your error)
    public Customer(String firstName, String lastName, String DOB,String email, String phone, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.DOB = DOB;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }


    // ✅ Getters and setters
    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getDOB() {
		return DOB;
	}


	public void setDOB(String dOB) {
		this.DOB = dOB;
	}
    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone;
    }

    public void setPhone_number(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // ✅ Optional: toString() method
    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", DOB='"  + DOB +'\''+
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }


	
}