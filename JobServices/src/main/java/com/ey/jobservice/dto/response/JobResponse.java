package com.ey.jobservice.dto.response;

import com.ey.jobservice.enums.JobStatus;
import com.ey.jobservice.enums.JobType;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class JobResponse {
	private Long id;
	private String title;
	private String description;
	private String location;
	private JobType jobType;
	private int experience;
	private Double salaryMin;
	private Double salaryMax;
	private String skills;
	private Long consultantId;
	private String companyName;
	private JobStatus status;
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
	public JobResponse(Long id, String title, String description, String location, JobType jobType, int experience,
			Double salaryMin, Double salaryMax, String skills, Long consultantId, String companyName,
			JobStatus status) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.location = location;
		this.jobType = jobType;
		this.experience = experience;
		this.salaryMin = salaryMin;
		this.salaryMax = salaryMax;
		this.skills = skills;
		this.consultantId = consultantId;
		this.companyName = companyName;
		this.status = status;
	}
	public JobResponse() {
		super();
	}
	

}
