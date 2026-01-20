package com.ey.jobportal.dto.response;

public class UserResponse {
	
	 	private Long id;
	    private String fullName;
	    private String email;
	    private String phoneNumber;
	    private String location;
	    private Integer experienceYears;
	    private String highestQualification;
	    private boolean active;
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
		public boolean isActive() {
			return active;
		}
		public void setActive(boolean active) {
			this.active = active;
		}
	    
	    
	 
	

}
