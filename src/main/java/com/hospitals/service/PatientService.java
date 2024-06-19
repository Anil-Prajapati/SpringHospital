package com.hospitals.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospitals.model.Patient;
import com.hospitals.repository.PatientRepository;

@Service
public class PatientService {

	@Autowired
	private PatientRepository patientRepository;

	public Iterable<Patient> getAll() {
		return patientRepository.findAll();
	}

	public Patient getSingleData(int id) {
		return patientRepository.findById(id).orElse(new Patient());
	}

	public Patient create(Patient patient) {
		patient.setPatientDate(new Date());
		return patientRepository.save(patient);
	}

	public Patient update(int id, String patientstatus) {
		Patient patient = patientRepository.findById(id).orElse(new Patient());
		patient.setPatientstatus(patientstatus);
		return patientRepository.save(patient);
	}
	
	public Patient updateDescription(int id,String descriptionDetails) {
		Patient description = patientRepository.findById(id).orElse(new Patient());
		description.setDescriptionDetails(descriptionDetails);
		return patientRepository.save(description);
	}
	
	
	public Map<String, Object> calculatePaidAmountMetrics() {
        Map<String, Object> metrics = new HashMap<>();

        Integer total = patientRepository.calculateTotalPaidAmount();
        Integer profit = patientRepository.calculateProfit();
        Integer loss = patientRepository.calculateLoss();
        Double average = patientRepository.calculateAveragePaidAmount();

        metrics.put("totalPaidAmount", total);
        metrics.put("profit", profit);
        metrics.put("loss", loss);
        metrics.put("averagePaidAmount", average);

        System.out.println("Total Paid Amount: " + total);
        System.out.println("Profit: " + profit);
        System.out.println("Loss: " + loss);
        System.out.println("Average Paid Amount: " + average);

        return metrics;
    }

}
