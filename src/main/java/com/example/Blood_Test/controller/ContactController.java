package com.example.Blood_Test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.Blood_Test.model.Contact;
import com.example.Blood_Test.service.ContactService;

@RestController
@CrossOrigin("http://localhost:4200/")
public class ContactController {

	@Autowired
	ContactService contactService;

	@RequestMapping(method = RequestMethod.POST, value = "/addFeedback")
	public void addFeedback(@RequestBody Contact contact) {
		contactService.addFeedback(contact);
	}

}
