package com.example.Blood_Test.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.Blood_Test.model.User;
import com.example.Blood_Test.service.PatientService;

@RestController
public class PatientController {
	
	@Autowired
	private PatientService patientService;
	@GetMapping("/allPatient")
	public List<User> getAllPatient() {
		return patientService.getAllPatient();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/addPatient")
	public void addPatient(@RequestBody User patient) {
		patientService.addPatient(patient);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/updatePatient/{id}")
	public void updatePatient(@PathVariable int id, @RequestBody User patient) {
		patientService.updatePatient(id, patient);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/deletePatient/{id}")
	public void deletePatient(@PathVariable int id) {
		patientService.deletePatient(id);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/loginValid")
	public User login(@RequestBody User user) {
		
		User oauthPatient = patientService.login(user.getEmail(), user.getPassword());
		
		if (Objects.nonNull(oauthPatient)) {
			return oauthPatient;
		}
		else {
			System.out.println("Inside else condition");
			return null;
		}

	}

	

}
