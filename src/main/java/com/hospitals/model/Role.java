package com.hospitals.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name="secure_role")
@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Role {
	
	@Id
	@NotEmpty
	@Column(name="role_name")
	private String roleName;
	
	@Column(name="role_description")
	private String description;
	
	@ManyToMany(mappedBy = "roles")
	@JsonIgnore
	private Set<User> users;

}
