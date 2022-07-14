package com.example.Blood_Test.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.Blood_Test.model.User;

public interface PatientRepository extends CrudRepository<User, Integer> {
	User findByEmailAndPassword(String email, String password);
	User findByEmail(String email) ;
}
