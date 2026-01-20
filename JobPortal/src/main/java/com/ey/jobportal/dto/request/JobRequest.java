package com.ey.jobportal.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
 
import java.time.LocalDate;
 
public class JobRequest {
 
    @NotBlank
    private String title;
 
    @NotBlank
    private String description;
 
    @NotBlank
    private String location;
 
    @NotNull
    private Double minSalary;
 
    @NotNull
    private Double maxSalary;
 
    private String employmentType;
    private String workMode;
    private Integer experienceRequired;
    private LocalDate applicationDeadline;
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
	public Double getMinSalary() {
		return minSalary;
	}
	public void setMinSalary(Double minSalary) {
		this.minSalary = minSalary;
	}
	public Double getMaxSalary() {
		return maxSalary;
	}
	public void setMaxSalary(Double maxSalary) {
		this.maxSalary = maxSalary;
	}
	public String getEmploymentType() {
		return employmentType;
	}
	public void setEmploymentType(String employmentType) {
		this.employmentType = employmentType;
	}
	public String getWorkMode() {
		return workMode;
	}
	public void setWorkMode(String workMode) {
		this.workMode = workMode;
	}
	public Integer getExperienceRequired() {
		return experienceRequired;
	}
	public void setExperienceRequired(Integer experienceRequired) {
		this.experienceRequired = experienceRequired;
	}
	public LocalDate getApplicationDeadline() {
		return applicationDeadline;
	}
	public void setApplicationDeadline(LocalDate applicationDeadline) {
		this.applicationDeadline = applicationDeadline;
	}

    
    
	
}
