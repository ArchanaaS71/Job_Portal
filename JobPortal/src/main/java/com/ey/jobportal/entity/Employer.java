package com.ey.jobportal.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
 
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
 
@Entity
@Table(name = "employers")
public class Employer {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @NotBlank
    private String companyName;
 
    @Email
    @NotBlank
    @Column(unique = true)
    private String email;
 
    @NotBlank
    private String password;
 
    private String companyWebsite;
 
    private String industry;
 
    @Column(length = 2000)
    private String companyDescription;
 
    private Integer companySize;
 
    private String headquartersLocation;
 
    private LocalDate foundedDate;
 
    private boolean active = true;
 
    @OneToMany(mappedBy = "employer", cascade = CascadeType.ALL)
    private List<Job> jobs;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCompanyWebsite() {
		return companyWebsite;
	}

	public void setCompanyWebsite(String companyWebsite) {
		this.companyWebsite = companyWebsite;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getCompanyDescription() {
		return companyDescription;
	}

	public void setCompanyDescription(String companyDescription) {
		this.companyDescription = companyDescription;
	}

	public Integer getCompanySize() {
		return companySize;
	}

	public void setCompanySize(Integer companySize) {
		this.companySize = companySize;
	}

	public String getHeadquartersLocation() {
		return headquartersLocation;
	}

	public void setHeadquartersLocation(String headquartersLocation) {
		this.headquartersLocation = headquartersLocation;
	}

	public LocalDate getFoundedDate() {
		return foundedDate;
	}

	public void setFoundedDate(LocalDate foundedDate) {
		this.foundedDate = foundedDate;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	public Employer(Long id, @NotBlank String companyName, @Email @NotBlank String email, @NotBlank String password,
			String companyWebsite, String industry, String companyDescription, Integer companySize,
			String headquartersLocation, LocalDate foundedDate, boolean active, List<Job> jobs) {
		super();
		this.id = id;
		this.companyName = companyName;
		this.email = email;
		this.password = password;
		this.companyWebsite = companyWebsite;
		this.industry = industry;
		this.companyDescription = companyDescription;
		this.companySize = companySize;
		this.headquartersLocation = headquartersLocation;
		this.foundedDate = foundedDate;
		this.active = active;
		this.jobs = jobs;
	}

	public Employer() {
		super();
	}

	public void setRoles(Set<Role> of) {
		// TODO Auto-generated method stub
		
	}
    
    
    
    
}    