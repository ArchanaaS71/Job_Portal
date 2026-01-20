package com.ey.jobportal.entity;

import jakarta.persistence.*;
 
import java.time.LocalDate;
 
@Entity
@Table(name = "job_applications")
public class JobApplication {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @ManyToOne
    private User user;
 
    @ManyToOne
    private Job job;
 
    private String status;
   
    private LocalDate appliedDate = LocalDate.now();
 
    private LocalDate statusUpdatedDate;
 
    private String recruiterRemarks;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getAppliedDate() {
		return appliedDate;
	}

	public void setAppliedDate(LocalDate appliedDate) {
		this.appliedDate = appliedDate;
	}

	public LocalDate getStatusUpdatedDate() {
		return statusUpdatedDate;
	}

	public void setStatusUpdatedDate(LocalDate statusUpdatedDate) {
		this.statusUpdatedDate = statusUpdatedDate;
	}

	public String getRecruiterRemarks() {
		return recruiterRemarks;
	}

	public void setRecruiterRemarks(String recruiterRemarks) {
		this.recruiterRemarks = recruiterRemarks;
	}

	public JobApplication(Long id, User user, Job job, String status, LocalDate appliedDate,
			LocalDate statusUpdatedDate, String recruiterRemarks) {
		super();
		this.id = id;
		this.user = user;
		this.job = job;
		this.status = status;
		this.appliedDate = appliedDate;
		this.statusUpdatedDate = statusUpdatedDate;
		this.recruiterRemarks = recruiterRemarks;
	}

	public JobApplication() {
		super();
	}
 
	
    
}