package com.example.Blood_Test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Blood_Test.model.Contact;
import com.example.Blood_Test.repository.ContactRepository;

@Service
public class ContactService {

	@Autowired
	public ContactRepository contactRepo;

	public void addFeedback(Contact contact) {
		contactRepo.save(contact);
	}
}
