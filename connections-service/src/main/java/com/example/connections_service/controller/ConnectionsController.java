package com.example.connections_service.controller;

import com.example.connections_service.entity.Person;
import com.example.connections_service.service.ConnectionsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/core")
@AllArgsConstructor
public class ConnectionsController {
    private final ConnectionsService connectionsService;
    @GetMapping("/{userId}/first-degree")
    ResponseEntity<List<Person>> getFirstDegree(@PathVariable Long userId){
        System.out.println("i am prabhakar rana !!!");
        return ResponseEntity.ok(connectionsService.getFirstDegree(userId));
    }
}
