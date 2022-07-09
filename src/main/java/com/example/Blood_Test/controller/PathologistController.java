package com.example.Blood_Test.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.Blood_Test.model.Pathologist;
import com.example.Blood_Test.service.PathologistService;

@RestController
public class PathologistController {

	@Autowired
	private PathologistService pathologistService;
	private Pathologist currentPatho; 
	@GetMapping("/allPathologist")
	public List<Pathologist> getAllPathologist() {
		return pathologistService.getAllPathologist();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/addPathologist")
	public void addPathologist(@RequestBody Pathologist pathologist) {
		pathologistService.addPathologist(pathologist);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/updatePathologist/{id}")
	public void updatePathologist(@PathVariable int id, @RequestBody Pathologist pathologist) {
		pathologistService.updatePathologist(id, pathologist);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/deletePathologist/{id}")
	public void deletePathologist(@PathVariable int id) {
		pathologistService.deletePathologist(id);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/loginPatho")
	public String login(@RequestBody Pathologist user) {
		System.out.println("value of Pathologist in controller : " + user);
		Pathologist oauthPathologist = pathologistService.loginPath(user.getEmail(), user.getPassword());
		
		if (Objects.nonNull(oauthPathologist)) {
			setCurrentPatho(oauthPathologist);
			String userName = oauthPathologist.getOwner_name();
			System.out.println("Inside if condition");
			return "DashboardPathologist " + userName;
		}
		else {
			System.out.println("Inside else condition");
			return "Login";
		}

	}

	public Pathologist getCurrentPatho() {
		return currentPatho;
	}

	public void setCurrentPatho(Pathologist currentPatho) {
		this.currentPatho = currentPatho;
	}

}
