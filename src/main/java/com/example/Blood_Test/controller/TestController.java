package com.example.Blood_Test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.Blood_Test.model.Test;
import com.example.Blood_Test.service.TestService;

@RestController
@CrossOrigin("http://localhost:4200/")
public class TestController {

	@Autowired
	private TestService testService;

	@GetMapping("/allTest")
	public List<Test> getAllTest() {
		return testService.getAllTest();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/addNewTest")
	public void addNewtest(@RequestBody Test test) {
		testService.addTest(test);
	}
}
