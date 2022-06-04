package com.example.Blood_Test.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Blood_Test.model.Test;
import com.example.Blood_Test.repository.TestRepository;

@Service
public class TestService {

	@Autowired
	public TestRepository testRepo;

	public List<Test> getAllTest() {
		List<Test> tests = new ArrayList<>();
		testRepo.findAll().forEach(tests::add);
		return tests;
	}

	public void addTest(Test test) {
		testRepo.save(test); // adding comment
	}
}
