package com.example.Blood_Test.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.Blood_Test.model.Report;
import com.example.Blood_Test.service.ReportService;

@RestController
public class ReportController {
	@Autowired
	private ReportService reportService ;

	@GetMapping("/allReports")
	public List<Report> getAllTest() {
		return reportService.getAllReports();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/addNewReport")
	public Map addNewReport(@RequestBody Report report) {
		Report savedReport = reportService.addReport(report);
		String filename = savedReport.getreport_id()+"-Report.pdf";
		return Map.of("filename",filename);
	}
}
