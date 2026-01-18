package com.ey.jobservice.entity;

import java.time.LocalDateTime;

import com.ey.jobservice.enums.JobStatus;
import com.ey.jobservice.enums.JobType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="jobs")
public class Job {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	@Size(min=5)
	private String title;
	@NotBlank
	@Column(length=2000)
	private String description;
	@NotBlank
	private String location;
	@NotNull
	@Enumerated(EnumType.STRING)
	private JobType jobType;
	@Min(0)
	private int experience;
	
	private Double salaryMin;
	private Double salaryMax;
	@NotBlank
	private String skills;
	@NotNull
	private Long consultantId;
	@NotBlank
	private String companyName;
	@Enumerated(EnumType.STRING)
	private JobStatus status = JobStatus.OPEN;
	
	private LocalDateTime createdAt = LocalDateTime.now();
	private LocalDateTime updatedAt;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public JobType getJobType() {
		return jobType;
	}
	public void setJobType(JobType jobType) {
		this.jobType = jobType;
	}
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	public Double getSalaryMin() {
		return salaryMin;
	}
	public void setSalaryMin(Double salaryMin) {
		this.salaryMin = salaryMin;
	}
	public Double getSalaryMax() {
		return salaryMax;
	}
	public void setSalaryMax(Double salaryMax) {
		this.salaryMax = salaryMax;
	}
	public String getSkills() {
		return skills;
	}
	public void setSkills(String skills) {
		this.skills = skills;
	}
	public Long getConsultantId() {
		return consultantId;
	}
	public void setConsultantId(Long consultantId) {
		this.consultantId = consultantId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public JobStatus getStatus() {
		return status;
	}
	public void setStatus(JobStatus status) {
		this.status = status;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	

}
