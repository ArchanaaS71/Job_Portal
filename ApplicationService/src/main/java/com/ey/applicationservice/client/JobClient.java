package com.ey.applicationservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="JobServices",path="/api/jobs")
public interface JobClient {

	@GetMapping("/{id}")
	Object getJobById(@PathVariable Long id);
}
