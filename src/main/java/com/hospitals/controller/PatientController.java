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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name="Patient Controller", description = "This Is The Patient Controller Here Patient Realated Work Is Visible")
@RequestMapping("/patient")
public class PatientController {
	
	@Autowired
	private PatientService patientService;
	
	@Autowired
	private AdminService adminService;
	
	
	@GetMapping("/all")
	@Operation(summary = "Here Patient Details Admin And Doctor We Acceess", description = "This Details We Are Visible Admin And Doctor Only Other Can Not See")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Geting Data Successfully"),
			@ApiResponse(responseCode = "401",description = "Something Wan't Wrong Plsease Trying Again"),
			@ApiResponse(responseCode = "402", description = "Data not Found")
	})
	@SecurityRequirement(name="Bearer Authentication")
	@PreAuthorize("hasAnyRole('Doctor','Admin')")
	public Iterable<Patient> getAll(){
		return patientService.getAll();
	}
	
	@GetMapping("/admin/all")
	@Operation(summary = "Patient We See All The Doctors", description = "Patient We See All The Doctor And Book The Appoiment")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Geting Data Successfully"),
			@ApiResponse(responseCode = "401",description = "Something Wan't Wrong Plsease Trying Again"),
			@ApiResponse(responseCode = "402", description = "Data not Found")
	})
	@SecurityRequirement(name="Bearer Authentication")
	@PreAuthorize("hasAnyRole('User')")
	public Iterable<Admin> getAllAdmin(){
		return adminService.getAll();
	}
	
	
	@GetMapping("/{id}")
	@Operation(summary = "Admin And Doctor Check The Patient Detail Using Id", description = "Admin And Doctor We See The Details Using Patient Id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Geting Data Successfully"),
			@ApiResponse(responseCode = "401",description = "Something Wan't Wrong Plsease Trying Again"),
			@ApiResponse(responseCode = "402", description = "Data not Found")
	})
	@SecurityRequirement(name="Bearer Authentication")
	@PreAuthorize("hasAnyRole('User','Doctor')")
	public Patient getSingle(@PathVariable("id") int id) {
		return patientService.getSingleData(id);
	}
	
	
	@PostMapping("/create")
	@Operation(summary = "Patient Book The Appoiment Using Doctor Specilization", description = "Patient Book The Appoiment Using Doctor Specilization That Doctor Notification Come")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Appoiment Book Successfully"),
			@ApiResponse(responseCode = "401",description = "Something Wan't Wrong Plsease Trying Again"),
			@ApiResponse(responseCode = "402", description = "Data not Found")
	})
	@SecurityRequirement(name="Bearer Authentication")
	@PreAuthorize("hasAnyRole('User')")
	public Patient create(@RequestBody Patient patient) {
		return patientService.create(patient);
	}
	
	@PutMapping("/{id}/{patientstatus}")
	@Operation(summary = "Doctor We Update The Status of Patient", description = "After Appoiment Booking Completed That Time Doctor See The Patient And Then Update The Status Of The Patient")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Geting Data Successfully"),
			@ApiResponse(responseCode = "401",description = "Something Wan't Wrong Plsease Trying Again"),
			@ApiResponse(responseCode = "402", description = "Data not Found")
	})
	@SecurityRequirement(name="Bearer Authentication")
	@PreAuthorize("hasAnyRole('Doctor')")
	public Patient update(@PathVariable("id") int id, @PathVariable("patientstatus") String patientstatus) {
		return patientService.update(id, patientstatus);
	}
	
	
	@PutMapping("/description/{id}/{descriptionDetails}")
	@Operation(summary = "Here Doctor Write The Description", description = "Here We Write The Description What Are The Issue Is Facing Patient")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Description Updated Successfully"),
			@ApiResponse(responseCode = "401",description = "Something Wan't Wrong Plsease Trying Again"),
			@ApiResponse(responseCode = "402", description = "Data not Found")
	})
	@SecurityRequirement(name="Bearer Authentication")
	@PreAuthorize("hasAnyRole('Doctor')")
	public Patient descriptionDetails( @PathVariable("id") int id,@PathVariable("descriptionDetails") String descriptionDetails) {
		return patientService.updateDescription(id, descriptionDetails);
	}
	
	@GetMapping("/amount")
	@Operation(summary = "Here Doctor And Admin We Are See The Growth Of The Hospitals", description = "Here Admin We See the Growth Of The Hospitals")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "Growth Of The Details Here"),
			@ApiResponse(responseCode = "401",description = "Something Wan't Wrong Plsease Trying Again"),
			@ApiResponse(responseCode = "402", description = "Data not Found")
	})
	@SecurityRequirement(name="Bearer Authentication")
    @PreAuthorize("hasAnyRole('Doctor','Admin')")
    public ResponseEntity<Map<String, Object>> calculatePaidAmount() {
        Map<String, Object> metrics = patientService.calculatePaidAmountMetrics();
        System.out.println("Amount Details getting successfully.");
        return ResponseEntity.ok(metrics);
    }
 
}
