package com.hospitals.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hospitals.model.Admin;
import com.hospitals.service.AdminService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name="Admin Controller", description = "Here Maitain The Admin Realated Resposbility")
public class AdminController {
	
	@Autowired
	private AdminService doctorService;
	
	@GetMapping("/all")
	@Operation(summary = "Get All The Details For Admin",description = "This Details Can Admin Only Access Other One is Not able to acess")
	@ApiResponse(responseCode = "200", description = "All Data Getting Successfully")
	@SecurityRequirement(name="Bearer Authentication")
	@PreAuthorize("hasAnyRole('Admin')")
	public Iterable<Admin> getAll(){
		return doctorService.getAll();
	}
	
	@GetMapping("/doctor/{id}")
	@Operation(summary = "Get Single Data For Admin", description = "Get The Details Using Id And This Details Can Only Admin Access Other One is Not able to acess")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Geting Data Successfully"),
			@ApiResponse(responseCode = "401", description = "Something Wan't Wrong Plsease Trying Again"),
			@ApiResponse(responseCode = "402", description = "Data not Found")
	})
	@SecurityRequirement(name="Bearer Authentication")
	@PreAuthorize("hasAnyRole('Admin')")
	public Admin getSingleDate(@PathVariable("id") Integer id) {
		return doctorService.getSingleData(id);
	}
	
	@PostMapping("/create")
	@Operation(summary = "Admin Only Add The Doctor",description = "Admin Only Add The Doctor And delete the Doctor also Updated")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Doctor Add Succefully In Your Databases"),
			@ApiResponse(responseCode = "401", description = "Something Wan't Wrong Pls Trying Again Latter")
	})
	@SecurityRequirement(name="Bearer Authentication")
	@PreAuthorize("hasAnyRole('Admin')")
	public Admin create(@RequestBody Admin doctor) {
		return doctorService.create(doctor);
	}
	
	@PutMapping("/update/{id}/{shiftTime}/{doctorFee}")
	@Operation(summary = "Admin Only Update The Doctor", description = "Admin Only Update The Doctor Details")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Doctor Update Succefully In Your Databases"),
			@ApiResponse(responseCode = "401", description = "Something Wan't Wrong Pls Trying Again Latter")
	})
	@SecurityRequirement(name="Bearer Authentication")
	@PreAuthorize("hasAnyRole('Admin')")
	public Admin update(@PathVariable("id") int id,
			@PathVariable("shiftTime") String shiftTime,
			@PathVariable("doctorFee") int doctorFee) {
		return doctorService.update(id, shiftTime, doctorFee);
	}
	
	@DeleteMapping("/delete/{id}")
	@Operation(summary = "Admin Only Delete The Doctor", description = "Admin Only Delete The Doctor From The Databases")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Doctor Delete Succefully In Your Databases"),
			@ApiResponse(responseCode = "401", description = "Something Wan't Wrong Pls Trying Again Latter")
	})
	@SecurityRequirement(name="Bearer Authentication")
	@PreAuthorize("hasAnyRole('Admin')")
	public void delete(@PathVariable("id") int id) {
		doctorService.DeleteData(id);
	}

}
