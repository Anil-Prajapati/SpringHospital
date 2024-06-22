package com.hospitals.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospitals.email.EmailNotification;
import com.hospitals.model.Admin;
import com.hospitals.repository.AdminRepository;

@Service
public class AdminService {
	
	@Autowired
	private AdminRepository doctorRepository;
	
	@Autowired
	private EmailNotification emailNotification;
	
	public Iterable<Admin> getAll(){
		return doctorRepository.findAll();
	}
	
	public Admin getSingleData(Integer id) {
		return doctorRepository.findById(id).orElse(new Admin());
	}
	
	public Admin create(Admin doctor) {
		doctor.setJoinDate(new Date());
		
		String emailContent = "<html>" +
		        "<head>" +
		        "<style>" +
		        "body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 0; }" +
		        ".container { width: 100%; max-width: 600px; margin: 0 auto; background-color: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); }" +
		        ".header { background-color: #516962; padding: 10px 0; color: white; text-align: center; border-radius: 8px 8px 0 0; }" +
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
		                "<h2>Offer Letter: Welcome to Sunita Hospital!</h2>" +
		                "<p>Hello " + doctor.getDoctorName() + ",</p>" +
		                "<p>We are pleased to offer you a position as a <strong>" + doctor.getDoctorSpecialization() + "</strong> at Sunita Hospital.</p>" +
		                "<p><strong>Terms of Employment:</strong></p>" +
		                "<ul>" +
		                    "<li>Position: " + doctor.getDoctorSpecialization() + "</li>" +
		                    "<li>Start Date: " + doctor.getJoinDate() + "</li>" +
		                "</ul>" +
		                "<p>Please review the attached contract for detailed terms and conditions. We are excited to have you join our team and contribute to our mission of providing exceptional healthcare services.</p>" +
		                "<p>If you have any questions or need further information, please contact our HR department at <a href='mailto:hr@sunitahospital.com'>hr@sunitahospital.com</a>.</p>" +
		            "</div>" +
		            "<div class='footer'>" +
		                "<p>Best regards,<br>The Sunita Hospital Team</p>" +
		                "<p><br>admin@sunitahospital.com</p>" +
		                "<p><br>8081489506</p>" +
		            "</div>" +
		        "</div>" +
		        "</body>" +
		        "</html>";
		
		emailNotification.mailSender("Doctor Account Created",emailContent, doctor.getDoctorEmail());
		
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
