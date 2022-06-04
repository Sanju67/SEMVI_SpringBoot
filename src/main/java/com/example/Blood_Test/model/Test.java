package com.example.Blood_Test.model;

import java.io.File;
import java.util.Date;

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
	private String patient_name;
	private String doctor_name;
	private File Prescription_file;
	private String contact_no;
	private String test_location;
	private String address;
	private String test_type;
	private Date test_date;

	public int getTest_id() {
		return test_id;
	}

	public void setTest_id(int test_id) {
		this.test_id = test_id;
	}

	public String getPatient_name() {
		return patient_name;
	}

	public void setPatient_name(String patient_name) {
		this.patient_name = patient_name;
	}

	public String getDoctor_name() {
		return doctor_name;
	}

	public void setDoctor_name(String doctor_name) {
		this.doctor_name = doctor_name;
	}

	public File getPrescription_file() {
		return Prescription_file;
	}

	public void setPrescription_file(File prescription_file) {
		Prescription_file = prescription_file;
	}

	public String getContact_no() {
		return contact_no;
	}

	public void setContact_no(String contact_no) {
		this.contact_no = contact_no;
	}

	public String getTest_location() {
		return test_location;
	}

	public void setTest_location(String test_location) {
		this.test_location = test_location;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTest_type() {
		return test_type;
	}

	public void setTest_type(String test_type) {
		this.test_type = test_type;
	}

	public Date getTest_date() {
		return test_date;
	}

	public void setTest_date(Date test_date) {
		this.test_date = test_date;
	}

}
