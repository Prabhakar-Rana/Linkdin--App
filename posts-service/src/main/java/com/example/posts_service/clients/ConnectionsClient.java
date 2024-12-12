package com.example.posts_service.clients;

import com.example.posts_service.dto.PersonDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "connection-service") // Replace with your service name and URL
public interface ConnectionsClient {
    @GetMapping("/core/first-degree")
    List<PersonDto> getFirstConnections();
}
