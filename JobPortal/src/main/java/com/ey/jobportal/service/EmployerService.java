 
package com.ey.jobportal.service;
 
import java.util.List;
 
import com.ey.jobportal.dto.response.EmployerResponse;
import com.ey.jobportal.dto.response.JobResponse;
 
public interface EmployerService {
 
	  EmployerResponse getEmployerById(Long id);
	  
	    List<EmployerResponse> getAllEmployers();
	 
	    void deactivateEmployer(Long id);
	 
	    void activateEmployer(Long id);
	 
	    List<JobResponse> getJobsByEmployer(Long employerId);
	 
	    long countJobsByEmployer(Long employerId);
	 
	    boolean isEmployerActive(Long id);
}