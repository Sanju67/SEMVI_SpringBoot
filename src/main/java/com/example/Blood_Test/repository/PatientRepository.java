package com.example.Blood_Test.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.Blood_Test.model.Patient;

public interface PatientRepository extends CrudRepository<Patient, Integer> {
	Patient findByEmailAndPassword(String email, String password);
}
