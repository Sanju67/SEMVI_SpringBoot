package com.example.Blood_Test.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Blood_Test.model.Report;
import com.example.Blood_Test.repository.ReportRepository;


@Service
public class ReportService {
	@Autowired
	public ReportRepository reportRepo;

	public List<Report> getAllReports() {
		List<Report> tests = new ArrayList<>();
		reportRepo.findAll().forEach(tests::add);
		return tests;
	}

	public void addReport(Report report) {
		reportRepo.save(report); // adding comment
	}

}
