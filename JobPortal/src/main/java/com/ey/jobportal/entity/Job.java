package com.ey.jobportal.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
 
import java.time.LocalDate;
import java.util.Set;
 
@Entity
@Table(name = "jobs")
public class Job {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @NotBlank
    private String title;
 
    @NotBlank
    @Column(length = 3000)
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
 
    private LocalDate postedDate = LocalDate.now();
 
    private LocalDate applicationDeadline;
 
    private boolean active = true;
 
    @ManyToOne
    @JoinColumn(name = "employer_id")
    private Employer employer;
 
    @ManyToMany
    @JoinTable(
            name = "job_skills",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private Set<Skill> requiredSkills;

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

	public LocalDate getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(LocalDate postedDate) {
		this.postedDate = postedDate;
	}

	public LocalDate getApplicationDeadline() {
		return applicationDeadline;
	}

	public void setApplicationDeadline(LocalDate applicationDeadline) {
		this.applicationDeadline = applicationDeadline;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Employer getEmployer() {
		return employer;
	}

	public void setEmployer(Employer employer) {
		this.employer = employer;
	}

	public Set<Skill> getRequiredSkills() {
		return requiredSkills;
	}

	public void setRequiredSkills(Set<Skill> requiredSkills) {
		this.requiredSkills = requiredSkills;
	}

	public Job(Long id, @NotBlank String title, @NotBlank String description, @NotBlank String location,
			@NotNull Double minSalary, @NotNull Double maxSalary, String employmentType, String workMode,
			Integer experienceRequired, LocalDate postedDate, LocalDate applicationDeadline, boolean active,
			Employer employer, Set<Skill> requiredSkills) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.location = location;
		this.minSalary = minSalary;
		this.maxSalary = maxSalary;
		this.employmentType = employmentType;
		this.workMode = workMode;
		this.experienceRequired = experienceRequired;
		this.postedDate = postedDate;
		this.applicationDeadline = applicationDeadline;
		this.active = active;
		this.employer = employer;
		this.requiredSkills = requiredSkills;
	}

	public Job() {
		super();
	}

	

	
    
    
    
}
 
