package com.ey.jobportal.mapper;

import com.ey.jobportal.dto.request.RegisterEmployerRequest;
import com.ey.jobportal.dto.response.EmployerResponse;
import com.ey.jobportal.entity.Employer;

public class EmployerMapper {
	 public static Employer toEntity(RegisterEmployerRequest request) {
		 
	        if (request == null) {
	            return null;
	        }
	 
	        Employer employer = new Employer();
	        employer.setCompanyName(request.getCompanyName());
	        employer.setEmail(request.getEmail());
	        employer.setPassword(request.getPassword());
	        employer.setCompanyWebsite(request.getCompanyWebsite());
	        employer.setIndustry(request.getIndustry());
	        employer.setCompanyDescription(request.getCompanyDescription());
	        employer.setCompanySize(request.getCompanySize());
	        employer.setHeadquartersLocation(request.getHeadquartersLocation());
	        employer.setFoundedDate(request.getFoundedDate());
	        employer.setActive(true);
	 
	        return employer;
	    }
	 
	  
	    public static EmployerResponse toResponse(Employer employer) {
	 
	        if (employer == null) {
	            return null;
	        }
	 
	        EmployerResponse response = new EmployerResponse();
	        response.setId(employer.getId());
	        response.setCompanyName(employer.getCompanyName());
	        response.setEmail(employer.getEmail());
	        response.setIndustry(employer.getIndustry());
	        response.setHeadquartersLocation(employer.getHeadquartersLocation());
	        response.setActive(employer.isActive());
	 
	        return response;
	    }
	

}
