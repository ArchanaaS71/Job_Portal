package com.ey.applicationservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="NotificationService",path="/api/notifications")
public interface NotificationClient {
	
	@PostMapping("/send")
	void sendNotification(@RequestBody Object request);

}
