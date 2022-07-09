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
import com.example.Blood_Test.model.Patient;
import com.example.Blood_Test.service.PatientService;

@RestController
public class PatientController {

	@Autowired
	private PatientService patientService;
	
	@GetMapping("/allPatient")
	public List<Patient> getAllPatient() {
		return patientService.getAllPatient();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/addPatient")
	public void addPatient(@RequestBody Patient patient) {
		patientService.addPatient(patient);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/updatePatient/{id}")
	public void updatePatient(@PathVariable int id, @RequestBody Patient patient) {
		patientService.updatePatient(id, patient);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/deletePatient/{id}")
	public void deletePatient(@PathVariable int id) {
		patientService.deletePatient(id);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/loginValid")
	public String login(@RequestBody Patient user) {
		System.out.println("value of Patient in controller : " + user);
		Patient oauthPatient = patientService.login(user.getEmail(), user.getPassword());
		
		if (Objects.nonNull(oauthPatient)) {
			String userName = oauthPatient.getFirstName() + " " + oauthPatient.getLastName();
			System.out.println("Inside if condition");
			return "DashboardPatient " + userName;
		}
		else {
			System.out.println("Inside else condition");
			return "Login";
		}

	}

}
