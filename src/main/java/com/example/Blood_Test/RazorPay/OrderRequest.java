package com.example.Blood_Test.RazorPay;

import lombok.Data;

@Data
public class OrderRequest {
    private String customerName;
    private String email;
    private String phoneNumber;
    private String amount;
    private int user_id;
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public int getUser_id() {
		return user_id ;
	}
	public void setUser_id(int id) {
		this.user_id = id;
	}
	
    
    
}
