package com.ey.jobportal.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
 
import java.time.LocalDate;
import java.util.Set;

import com.ey.jobportal.enums.RoleName;
 
@Entity
@Table(name = "users")
public class User {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @NotBlank
    @Size(min = 3, max = 60)
    private String fullName;
 
    @Email
    @NotBlank
    @Column(unique = true)
    private String email;
 
    @NotBlank
    private String password;
 
    @Pattern(regexp = "^[0-9]{10}$")
    private String phoneNumber;
 
    private String location;
 
    private Integer experienceYears;
 
    private String highestQualification;
 
    @Column(length = 2000)
    private String resumeSummary;
 
    private LocalDate dateOfBirth;
 
    private boolean active = true;
 
    private boolean emailVerified = false;
 
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;
 
    @ManyToMany
    @JoinTable(
            name = "user_skills",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private Set<Skill> skills;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getExperienceYears() {
		return experienceYears;
	}

	public void setExperienceYears(Integer experienceYears) {
		this.experienceYears = experienceYears;
	}

	public String getHighestQualification() {
		return highestQualification;
	}

	public void setHighestQualification(String highestQualification) {
		this.highestQualification = highestQualification;
	}

	public String getResumeSummary() {
		return resumeSummary;
	}

	public void setResumeSummary(String resumeSummary) {
		this.resumeSummary = resumeSummary;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isEmailVerified() {
		return emailVerified;
	}

	public void setEmailVerified(boolean emailVerified) {
		this.emailVerified = emailVerified;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<Skill> getSkills() {
		return skills;
	}

	public void setSkills(Set<Skill> skills) {
		this.skills = skills;
	}

	public User(Long id, @NotBlank @Size(min = 3, max = 60) String fullName, @Email @NotBlank String email,
			@NotBlank String password, @Pattern(regexp = "^[0-9]{10}$") String phoneNumber, String location,
			Integer experienceYears, String highestQualification, String resumeSummary, LocalDate dateOfBirth,
			boolean active, boolean emailVerified, Set<Role> roles, Set<Skill> skills) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.location = location;
		this.experienceYears = experienceYears;
		this.highestQualification = highestQualification;
		this.resumeSummary = resumeSummary;
		this.dateOfBirth = dateOfBirth;
		this.active = active;
		this.emailVerified = emailVerified;
		this.roles = roles;
		this.skills = skills;
	}

	public User() {
		super();
	}

	
    
    
    
}