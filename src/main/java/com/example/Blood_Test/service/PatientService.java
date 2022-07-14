package com.example.Blood_Test.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Blood_Test.model.User;
import com.example.Blood_Test.repository.PatientRepository;

@Service
public class PatientService {

	@Autowired
	public PatientRepository patientRepo;

	public List<User> getAllPatient() {
		List<User> patients = new ArrayList<>();
		patientRepo.findAll().forEach(patients::add);
		return patients;
	}

	public void addPatient(User patient) {
		patientRepo.save(patient);
	}

	public void updatePatient(User patient) {
		User patientToUpdate = patientRepo.findByEmail(patient.getEmail());
		System.out.println("User got by mail id : " + patientToUpdate.getFirstName());
		patientToUpdate.setPassword(patient.getPassword());
		patientRepo.save(patientToUpdate);
	}

	public void deletePatient(int id) {
		patientRepo.deleteById(id);
	}

	public User login(String email, String password) {
		User patient = patientRepo.findByEmailAndPassword(email, password);
		return patient;
	}
}
