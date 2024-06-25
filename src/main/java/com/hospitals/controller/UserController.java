package com.hospitals.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospitals.model.Admin;
import com.hospitals.model.User;
import com.hospitals.service.AdminService;
import com.hospitals.service.UserService;
import com.hospitals.util.ApiComponent;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AdminService doctorService;
	
	@GetMapping(ApiComponent.GET_API)
	public Iterable<User> getAll(){
		return userService.getAll(); 
	}
	
	@GetMapping("/names/{userName}")
	@PreAuthorize("hasAnyRole('Admin','Doctor','User')")
	public User getSingle(@PathVariable("userName") String userName) {
		return userService.getSingleData(userName);
	}
	
	@PostMapping(ApiComponent.CREATE_API)
	public User create(@RequestBody User user) {
		return userService.create(user);
	}
	
	@PreAuthorize("hasAnyRole('Admin')")
	@PostMapping("/add/doctor")
	public Admin AddDoctor(@RequestBody Admin doctor) {
		return doctorService.create(doctor);
	}
	
	@GetMapping(value = "/Admin")
	@PreAuthorize("hasAnyRole('Admin')")
	public String admin() {
		return "This Is The Admin age...";
	}

	
	//it is possible to access user and admin any other can not access
	@GetMapping(value = "/User")
	@PreAuthorize("hasAnyRole('Admin','User')")
	public String user() {
		return "This Is The User Page...";
	}
	
	@GetMapping("/testing")
	public String NoramalTesting() {
		return "This Api Without Any security Runing. when we are deploye the projects some where pls test this api first and after that we do the security content";
	}

}
