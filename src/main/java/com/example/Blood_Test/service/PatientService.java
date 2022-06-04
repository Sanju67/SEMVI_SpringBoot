package com.example.Blood_Test.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Blood_Test.model.Patient;
import com.example.Blood_Test.repository.PatientRepository;

@Service
public class PatientService {

	@Autowired
	public PatientRepository patientRepo;

	public List<Patient> getAllPatient() {
		List<Patient> patients = new ArrayList<>();
		patientRepo.findAll().forEach(patients::add);
		return patients;
	}

	public void addPatient(Patient patient) {
		patientRepo.save(patient);
	}

	public void updatePatient(int id, Patient patient) {
		patientRepo.save(patient);
	}

	public void deletePatient(int id) {
		patientRepo.deleteById(id);
	}

	public Patient login(String email, String password) {
		Patient patient = patientRepo.findByEmailAndPassword(email, password);
		return patient;
	}
}
