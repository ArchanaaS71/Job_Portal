package com.ey.jobservice.dto.request;

import com.ey.jobservice.enums.JobType;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class JobRequest {

	@NotBlank
	private String title;
	@NotBlank
	private String description;
	@NotBlank
	private String location;
	@NotNull
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
	public JobRequest(@NotBlank String title, @NotBlank String description, @NotBlank String location,
			@NotNull JobType jobType, @Min(0) int experience, Double salaryMin, Double salaryMax,
			@NotBlank String skills, @NotNull Long consultantId, @NotBlank String companyName) {
		super();
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
	}
	public JobRequest() {
		super();
	}
	

}
