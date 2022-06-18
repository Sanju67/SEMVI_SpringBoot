package com.example.Blood_Test.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.Blood_Test.model.Pathologist;
import com.example.Blood_Test.model.Patient;

public interface PathologistRepository extends CrudRepository<Pathologist, Integer> {
	Pathologist findByEmailAndPassword(String email, String password);
}
