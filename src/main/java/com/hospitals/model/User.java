package com.hospitals.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "secure_table")
@Schema(name="User Model", description = "This Is The User Model")
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
@AllArgsConstructor
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@NotEmpty
	@Column(name = "user_name")
	private String userName;

	@NotEmpty
	@Column(name = "user_password")
	private String password;

	@Email
	@Column(name = "user_email")
	private String email;

	@Column(name = "user_address")
	private String address;

	@Column(name = "user_date")
	private Date date;

	@Column(name = "user_dob")
	private String dob;

	@Column(name = "user_contact")
	private long contactNumber;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name="user_roles",
	joinColumns = @JoinColumn(name="userName", referencedColumnName = "user_name"),
	inverseJoinColumns = @JoinColumn(name="roleName", referencedColumnName = "role_name")
	
			)
	private Set<Role> roles;

}
