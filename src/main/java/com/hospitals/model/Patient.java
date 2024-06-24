package com.hospitals.model;

import java.util.Date;

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
@Table(name = "patient_table")
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "patient_id")
	private int id;


	@Column(name = "patient_name")
	private String patientName;

	@Column(name = "patient_email")
	private String petientEmail;

	@Column(name = "patient_contact")
	private String patientContact;

	@Column(name = "patient_date")
	private Date patientDate;

	@Column(name = "patient_DOB")
	private String patientDOB;
	
	@Column(name="description_details")
	private String descriptionDetails;
	
	@Column(name = "patient_status")
	private String patientstatus;

	@Column(name = "patient_Address")
	private String patientAddress;
	
	@Column(name = "paid_Amount")
	private int paidAmount;
	
	@Column(name = "doctor_name")
	private String doctorName;

	public Patient(String patientName, String petientEmail, String patientContact, Date patientDate, String patientDOB,
			String descriptionDetails, String patientstatus, String patientAddress, int paidAmount, String doctorName) {
		super();
		this.patientName = patientName;
		this.petientEmail = petientEmail;
		this.patientContact = patientContact;
		this.patientDate = patientDate;
		this.patientDOB = patientDOB;
		this.descriptionDetails = descriptionDetails;
		this.patientstatus = patientstatus;
		this.patientAddress = patientAddress;
		this.paidAmount = paidAmount;
		this.doctorName = doctorName;
	}

	
}
