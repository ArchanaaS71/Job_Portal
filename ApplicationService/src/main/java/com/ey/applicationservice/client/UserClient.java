package com.ey.applicationservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="UserService",path="api/users")
public interface UserClient {


	@GetMapping("/{id}")
	Object getUserById(@PathVariable Long id);
	


}
