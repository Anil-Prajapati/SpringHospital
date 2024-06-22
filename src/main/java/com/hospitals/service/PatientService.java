package com.hospitals.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospitals.email.EmailNotification;
import com.hospitals.model.Patient;
import com.hospitals.repository.PatientRepository;

@Service
public class PatientService {

	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
	private EmailNotification emailNotification;

	public Iterable<Patient> getAll() {
		return patientRepository.findAll();
	}

	public Patient getSingleData(int id) {
		return patientRepository.findById(id).orElse(new Patient());
	}

	public Patient create(Patient patient) {
		patient.setPatientDate(new Date());
		
		String emailContent = "<html>" +
		        "<head>" +
		        "<style>" +
		        "body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 0; }" +
		        ".container { width: 100%; max-width: 600px; margin: 0 auto; background-color: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); }" +
		        ".header { background-color: #4CAF50; padding: 10px 0; color: white; text-align: center; border-radius: 8px 8px 0 0; }" +
		        ".content { padding: 25px; }" +
		        ".footer { text-align: center; font-size: 12px; color: #777777; margin-top: 20px; }" +
		        "</style>" +
		        "</head>" +
		        "<body>" +
		        "<div class='container'>" +
		            "<div class='header'>" +
		                "<h1>Welcome to Sunita Hospital!</h1>" +
		            "</div>" +
		            "<div class='content'>" +
		                "<p>Hello " + patient.getPatientName() + ",</p>" +
		                "<p>Congratulations! Your appointment has been successfully booked at Sunita Hospital.</p>" +
		                "<p><strong>Appointment Details:</strong></p>" +
		                "<p>- Appointment Book Date: " + patient.getPatientDate() + "</p>" +
		                "<p>- Appointment Book Time: " + patient.getPatientDOB() + "</p>" +
		                "<p>We look forward to seeing you at the appointment.</p>" +
		                "<p>If you have any questions or need to reschedule, please contact us.</p>" +
		            "</div>" +
		            "<div class='footer'>" +
		                "<p>Best regards,<br>The Sunita Hospital Team</p>" +
		                "<p>8081489506</p>" +
		            "</div>" +
		        "</div>" +
		        "</body>" +
		        "</html>";
		// Send the email notification
	    emailNotification.mailSender("Patient Appoiment Booked", emailContent, patient.getPetientEmail());
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
