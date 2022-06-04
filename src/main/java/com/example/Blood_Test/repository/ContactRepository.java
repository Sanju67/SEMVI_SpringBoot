package com.example.Blood_Test.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.Blood_Test.model.Contact;

public interface ContactRepository extends CrudRepository<Contact, Integer> {

}
