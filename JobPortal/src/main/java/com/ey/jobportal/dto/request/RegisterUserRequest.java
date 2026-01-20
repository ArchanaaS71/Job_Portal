package com.ey.jobportal.dto.request;

import jakarta.validation.constraints.*;

import java.time.LocalDate;
 
public class RegisterUserRequest {
 
    @NotBlank
    private String fullName;
 
    @Email
    @NotBlank
    private String email;
 
    @NotBlank
    @Size(min = 6)
    private String password;
 
    private String phoneNumber;
    private String location;
    private Integer experienceYears;
    private String highestQualification;
    private String resumeSummary;
    private LocalDate dateOfBirth;
 
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
}
 
