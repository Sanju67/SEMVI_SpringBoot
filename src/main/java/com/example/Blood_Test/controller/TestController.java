package com.example.Blood_Test.controller;

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
	 return Map.of("filename",filename);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/updateStatus")
	public void updateStatus(@RequestBody Test test) {
		System.out.println(" Test status is : "+test.getTestStatus());
		if (test.getTestStatus().equals("Pending")) {
			test.setTestStatus("Accepted");
			System.out.println(" Test status After setting is : "+test.getTestStatus());
			
		} else if(test.getTestStatus().equals("Accepted")) {
			test.setTestStatus("Completed");
			System.out.println(" Test status After setting is : "+test.getTestStatus());
		}
		
		testService.updateTest(test);
	}
}
