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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name="User Controller", description = "Here User Can Perform The Action What Is Required")
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AdminService doctorService;
	
	@GetMapping(ApiComponent.GET_API)
	@Operation(summary = "Get All The Details For User",description = "This Details Can User Only Here Is Not Add The Any Role")
	@ApiResponse(responseCode = "200", description = "Geting All Register Data Successfully")
	@PreAuthorize("hasAnyRole('Admin')")
	@SecurityRequirement(name = "Bearer Authentication")
	public Iterable<User> getAll(){
		return userService.getAll(); 
	}
	
	@GetMapping("/names/{userName}")
	@Operation(summary = "Get Single Data For Admin And User And Doctor", description = "Get The Details Using Id And This Details Can Only Admin And User And Doctor Access Other One is Not able to acess")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Geting Data Successfully"),
			@ApiResponse(responseCode = "401",description = "Something Wan't Wrong Plsease Trying Again"),
			@ApiResponse(responseCode = "402", description = "Data not Found")
	})
	@SecurityRequirement(name = "Bearer Authentication")
	@PreAuthorize("hasAnyRole('Admin','Doctor','User')")
	public User getSingle(@PathVariable("userName") String userName) {
		return userService.getSingleData(userName);
	}
	
	@PostMapping(ApiComponent.CREATE_API)
	@Operation(summary = "Here We Do The Registration Here Is Not Add Any Security", description = "Here We Do The Registration. This Api Is Every One We can Access And Do The Registration After That Login")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Your Registration Sucessfully Done"),
			@ApiResponse(responseCode = "401",description = "Something Wan't Wrong Plsease Trying Again"),
			@ApiResponse(responseCode = "402", description = "Data not Found")
	})
	@SecurityRequirement(name = "Bearer Authentication")
	public User create(@RequestBody User user) {
		return userService.create(user);
	}
	
	@PreAuthorize("hasAnyRole('Admin')")
	@Operation(summary = "Add Doctor For Admin", description = "Using This Api Admin We can Add The Doctor")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Creating Doctor Successfully"),
			@ApiResponse(responseCode = "401",description = "Something Wan't Wrong Plsease Trying Again"),
			@ApiResponse(responseCode = "402", description = "Data not Found")
	})

	@ApiResponse(responseCode = "200", description = "All Data Getting Successfully")
	@SecurityRequirement(name = "Bearer Authentication")
	@PostMapping("/add/doctor")
	public Admin AddDoctor(@RequestBody Admin doctor) {
		return doctorService.create(doctor);
	}
	
	@GetMapping(value = "/Admin")
	@Operation(summary = "Testing Admin Api", description = "testing Admin Api To Give The Response To Admin Accessable Details")
	@ApiResponse(responseCode = "200", description = "Admin Testing Api Here We Check Admin Role Only")
	@SecurityRequirement(name = "Bearer Authentication")
	@PreAuthorize("hasAnyRole('Admin')")
	public String admin() {
		return "This Is The Admin age...";
	}

	
	//it is possible to access user and admin any other can not access
	@GetMapping(value = "/User")
	@ApiResponse(responseCode = "200", description = "Here We Check That Admin And User We Are Able To Access This Details")
	@Operation(summary = "Testing Api Admin And User Can Access", description = "This Api Admin And User We Are Able To Access")
	@SecurityRequirement(name = "Bearer Authentication")
	@PreAuthorize("hasAnyRole('Admin','User')")
	public String user() {
		return "This Is The User Page...";
	}
	
	@GetMapping("/testing")
	@ApiResponse(responseCode = "200", description = "This Api We Are Noramally Access")
	@Operation(summary = "This Api Normally We Test Here Not Add the Security like Role", description = "This Api Access Noramlly No")
	@SecurityRequirement(name = "Bearer Authentication")
	public String NoramalTesting() {
		return "This Api Without Any security Runing. when we are deploye the projects some where pls test this api first and after that we do the security content";
	}

}
