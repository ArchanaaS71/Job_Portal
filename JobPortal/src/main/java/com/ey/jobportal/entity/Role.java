package com.ey.jobportal.entity;

import com.ey.jobportal.enums.RoleName;

import jakarta.persistence.*;
	 
@Entity
@Table(name = "roles")
public class Role {
	 
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 
	 @Enumerated(EnumType.STRING)
	 @Column(unique = true, nullable = false)
	 private RoleName name;
	 
	 private String description;

	 public Long getId() {
		 return id;
	 }

	 public void setId(Long id) {
		 this.id = id;
	 }

	 public RoleName getName() {
		 return name;
	 }

	 public void setName(RoleName name) {
		 this.name = name;
	 }

	 public String getDescription() {
		 return description;
	 }

	 public void setDescription(String description) {
		 this.description = description;
	 }
	 
}
