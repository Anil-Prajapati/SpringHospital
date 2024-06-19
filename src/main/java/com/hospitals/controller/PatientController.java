package com.hospitals.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospitals.model.Admin;
import com.hospitals.model.Patient;
import com.hospitals.service.AdminService;
import com.hospitals.service.PatientService;

@RestController
@RequestMapping("/patient")
public class PatientController {
	
	@Autowired
	private PatientService patientService;
	
	@Autowired
	private AdminService adminService;
	
	
	@GetMapping("/all")
	@PreAuthorize("hasAnyRole('Doctor','Admin')")
	public Iterable<Patient> getAll(){
		return patientService.getAll();
	}
	
	@GetMapping("/admin/all")
	@PreAuthorize("hasAnyRole('User')")
	public Iterable<Admin> getAllAdmin(){
		return adminService.getAll();
	}
	
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAnyRole('User','Doctor')")
	public Patient getSingle(@PathVariable("id") int id) {
		return patientService.getSingleData(id);
	}
	
	
	@PostMapping("/create")
	@PreAuthorize("hasAnyRole('User')")
	public Patient create(@RequestBody Patient patient) {
		return patientService.create(patient);
	}
	
	@PutMapping("/{id}/{patientstatus}")
	@PreAuthorize("hasAnyRole('Doctor')")
	public Patient update(@PathVariable("id") int id, @PathVariable("patientstatus") String patientstatus) {
		return patientService.update(id, patientstatus);
	}
	
	
	@PutMapping("/description/{id}/{descriptionDetails}")
	@PreAuthorize("hasAnyRole('Doctor')")
	public Patient descriptionDetails( @PathVariable("id") int id,@PathVariable("descriptionDetails") String descriptionDetails) {
		return patientService.updateDescription(id, descriptionDetails);
	}
	
	@GetMapping("/amount")
    @PreAuthorize("hasAnyRole('Doctor','Admin')")
    public ResponseEntity<Map<String, Object>> calculatePaidAmount() {
        Map<String, Object> metrics = patientService.calculatePaidAmountMetrics();
        System.out.println("Amount Details getting successfully.");
        return ResponseEntity.ok(metrics);
    }
 
}
