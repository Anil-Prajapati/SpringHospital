package com.hospitals.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospitals.model.Admin;
import com.hospitals.repository.AdminRepository;

@Service
public class AdminService {
	
	@Autowired
	private AdminRepository doctorRepository;
	
	public Iterable<Admin> getAll(){
		return doctorRepository.findAll();
	}
	
	public Admin getSingleData(Integer id) {
		return doctorRepository.findById(id).orElse(new Admin());
	}
	
	public Admin create(Admin doctor) {
		doctor.setJoinDate(new Date());
		return doctorRepository.save(doctor);
	}
	
	public Admin update(int id, String shiftTime, int doctorFee) {
		Admin doctor = doctorRepository.findById(id).orElse(new Admin());
		doctor.setShiftTime(shiftTime);
		doctor.setDoctorFee(doctorFee);
		
		return doctorRepository.save(doctor);
	}
	
	public void DeleteData(Integer id) {
		doctorRepository.deleteById(id);
	}

}
