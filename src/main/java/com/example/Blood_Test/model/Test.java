package com.example.Blood_Test.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "Test")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Test {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int test_id;
	private String patientName;
	private String doctorName;
	private String prescriptionFile;
	private String contactno;
	private String testType;
	private String testDate;
	private String testLocation;
	private String address;
	private String testStatus;
	private int user_id;
	
	public String getTestStatus() {
		return testStatus;
	}
	public void setTestStatus(String testStatus) {
		this.testStatus = testStatus;
	}
	public int getTest_id() {
		return test_id;
	}
	public void setTest_id(int test_id) {
		this.test_id = test_id;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getPrescriptionFile() {
		return prescriptionFile;
	}
	public void setPrescriptionFile(String prescriptionFile) {
		this.prescriptionFile = prescriptionFile;
	}
	public String getContactno() {
		return contactno;
	}
	public void setContactno(String contactno) {
		this.contactno = contactno;
	}
	public String getTestType() {
		return testType;
	}
	public void setTestType(String testType) {
		this.testType = testType;
	}
	public String getTestDate() {
		return testDate;
	}
	public void setTestDate(String testDate) {
		this.testDate = testDate;
	}
	public String getTestLocation() {
		return testLocation;
	}
	public void setTestLocation(String testLocation) {
		this.testLocation = testLocation;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}


}
