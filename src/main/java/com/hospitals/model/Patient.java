package com.hospitals.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
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

	public Patient(@NotEmpty String patientName, @NotEmpty String petientEmail, @NotEmpty String patientContact,
			@NotEmpty Date patientDate, @NotEmpty String patientDOB, @NotEmpty String patientAddress) {
		super();
		this.patientName = patientName;
		this.petientEmail = petientEmail;
		this.patientContact = patientContact;
		this.patientDate = patientDate;
		this.patientDOB = patientDOB;
		this.patientAddress = patientAddress;
	}
}
