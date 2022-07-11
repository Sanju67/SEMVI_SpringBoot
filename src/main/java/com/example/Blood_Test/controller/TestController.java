package com.example.Blood_Test.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.Blood_Test.model.Test;
import com.example.Blood_Test.service.TestService;

@RestController
public class TestController {

	@Autowired
	private TestService testService;

	@GetMapping("/allTest")
	public List<Test> getAllTest() {
		return testService.getAllTest();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/addNewTest")
	public Map addNewtest(@RequestBody Test test) {
	 Test savedTest = testService.addTest(test);
	 String filename = savedTest.getTest_id() +"-" +savedTest.getPatientName() +  "-PrescriptionFile.pdf";
	 savedTest.setPrescriptionFile(filename);
	 testService.updateTest(savedTest) ;
	 return Map.of("filename",savedTest.getTest_id() + "-test.pdf");
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/updateStatus")
	public void updateStatus(@RequestBody Test test) {
		test.setTestStatus("Accepted");
		testService.updateTest(test);
	}
}
