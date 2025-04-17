package com.hexaware.entity;

public class Customers {
    private int customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String model;

    public Customers() {
    }

    public Customers(int customerId, String firstName, String lastName, String email, String phone,String model) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.setModel(model);
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Customer [ID=" + customerId + ", First Name=" + firstName + ", Last Name=" + lastName +
               ", Email=" + email + ", Phone=" + phone + "]";
    }

	public String getContactNumber() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
}
