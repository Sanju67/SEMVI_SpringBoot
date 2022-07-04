package com.example.Blood_Test.model;

import java.io.File;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "Report")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Report {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int report_id;
	private String patientName;
	private String testType;
	private String reportDate ;
	private File reportFile ;
	
	
	public Report(int test_id, String patientName, String testType, String reportDate, File reportFile) {
		super();
		this.report_id = test_id;
		this.patientName = patientName;
		this.testType = testType;
		this.reportDate = reportDate;
		this.reportFile = reportFile;
	}
	public int getTest_id() {
		return report_id;
	}
	public void setTest_id(int test_id) {
		this.report_id = test_id;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getTestType() {
		return testType;
	}
	public void setTestType(String testType) {
		this.testType = testType;
	}
	public String getReportDate() {
		return reportDate;
	}
	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}
	public File getReportFile() {
		return reportFile;
	}
	public void setReportFile(File reportFile) {
		this.reportFile = reportFile;
	}

}
