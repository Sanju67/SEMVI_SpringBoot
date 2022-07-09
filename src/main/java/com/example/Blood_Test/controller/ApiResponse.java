package com.example.Blood_Test.controller;

import lombok.Value;

@Value
public class ApiResponse {
	private Boolean success;
	private String message;
	public ApiResponse(boolean b, String errorMsg) {
		// TODO Auto-generated constructor stub
	}
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
