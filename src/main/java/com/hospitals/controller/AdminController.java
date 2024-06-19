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

@RestController
public class AdminController {
	
	@Autowired
	private AdminService doctorService;
	
	@GetMapping("/all")
	@PreAuthorize("hasAnyRole('Admin')")
	public Iterable<Admin> getAll(){
		return doctorService.getAll();
	}
	
	@GetMapping("/doctor/{id}")
	@PreAuthorize("hasAnyRole('Admin')")
	public Admin getSingleDate(@PathVariable("id") Integer id) {
		return doctorService.getSingleData(id);
	}
	
	@PostMapping("/create")
	@PreAuthorize("hasAnyRole('Admin')")
	public Admin create(@RequestBody Admin doctor) {
		return doctorService.create(doctor);
	}
	
	@PutMapping("/update/{id}/{shiftTime}/{doctorFee}")
	public Admin update(@PathVariable("id") int id,
			@PathVariable("shiftTime") String shiftTime,
			@PathVariable("doctorFee") int doctorFee) {
		return doctorService.update(id, shiftTime, doctorFee);
	}
	
	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasAnyRole('Admin')")
	public void delete(@PathVariable("id") int id) {
		doctorService.DeleteData(id);
	}

}
