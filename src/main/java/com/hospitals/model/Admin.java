package com.hospitals.model;

import java.util.Date;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name="doctor_table")
@Schema(name="Doctor Model", description = "This Is The Doctor Model Here")
@SecurityScheme(
		name="Bearer Authentication",
		type = SecuritySchemeType.HTTP,
		bearerFormat = "JWT",
		scheme = "bearer"
		)
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Admin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="doctor_id")
	private int id;
	
	@Column(name="doctor_name")
	private String doctorName;
	
	@Column(name="doctor_email")
	private String doctorEmail;
	
	@Column(name="doctor_specialization")
	private String doctorSpecialization;
	
	@Column(name="doctor_education")
	private String doctorEducation;
	
	@Column(name="doctor_contact_number")
	private long contactNumber;
	
	@Column(name="doctor_shift_time")
	private String shiftTime;
	
	@Column(name="doctor_available")
	private String doctorAvailable;
	
	@Column(name="doctor_fee")
	private int doctorFee;
	
	@Column(name="doctor_join_date")
	private Date joinDate;
	
	@Column(name="doctor_ratting")
	private String doctorRating;

	public Admin(String doctorName, String doctorEmail, String doctorSpecialization, String doctorEducation,
			long contactNumber, String shiftTime, String doctorAvailable, int doctorFee, Date joinDate,
			String doctorRating) {
		super();
		this.doctorName = doctorName;
		this.doctorEmail = doctorEmail;
		this.doctorSpecialization = doctorSpecialization;
		this.doctorEducation = doctorEducation;
		this.contactNumber = contactNumber;
		this.shiftTime = shiftTime;
		this.doctorAvailable = doctorAvailable;
		this.doctorFee = doctorFee;
		this.joinDate = joinDate;
		this.doctorRating = doctorRating;
	}

	
}
