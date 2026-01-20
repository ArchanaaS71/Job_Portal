package com.ey.jobportal.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterEmployerRequest {

	   @NotBlank
	    private String companyName;
	 
	    @Email
	    @NotBlank
	    private String email;
	 
	    @NotBlank
	    @Size(min = 6)
	    private String password;
	 
	    private String companyWebsite;
	    private String industry;
	    private String companyDescription;
	    private Integer companySize;
	    private String headquartersLocation;
	    private LocalDate foundedDate;
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
	    
	    
}
