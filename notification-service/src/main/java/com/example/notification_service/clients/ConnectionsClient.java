package com.example.notification_service.clients;

import com.example.notification_service.dto.PersonDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "connection-service", url = "")
public interface ConnectionsClient {
     @GetMapping("/core/first-degree")
     List<PersonDto>getFirstDegree(@RequestParam("X-Authenticated-User")  Long id);
}
