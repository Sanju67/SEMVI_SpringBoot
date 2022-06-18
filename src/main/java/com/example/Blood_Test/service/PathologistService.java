package com.example.Blood_Test.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Blood_Test.model.Pathologist;
import com.example.Blood_Test.model.Patient;
import com.example.Blood_Test.repository.PathologistRepository;

@Service
public class PathologistService {

	@Autowired
	private PathologistRepository pathologistRepo;

	public List<Pathologist> getAllPathologist() {
		List<Pathologist> pathologists = new ArrayList<>();
		pathologistRepo.findAll().forEach(pathologists::add);
		return pathologists;
	}

	public void addPathologist(Pathologist pathologist) {
		pathologistRepo.save(pathologist);
	}

	public void updatePathologist(int id, Pathologist pathologist) {
		pathologistRepo.save(pathologist);
	}

	public void deletePathologist(int id) {
		pathologistRepo.deleteById(id);
	}
	
	public Pathologist loginPath(String email, String password) {
		Pathologist pathologist = pathologistRepo.findByEmailAndPassword(email, password);
		return pathologist;
	}
}
