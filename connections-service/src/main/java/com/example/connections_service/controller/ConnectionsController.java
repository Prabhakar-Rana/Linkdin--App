package com.example.connections_service.controller;

import com.example.connections_service.auth.UserContextHolder;
import com.example.connections_service.entity.Person;
import com.example.connections_service.service.ConnectionsService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/core")
@AllArgsConstructor
public class ConnectionsController {
    private final ConnectionsService connectionsService;
    @GetMapping("/first-degree")
    ResponseEntity<List<Person>> getFirstDegree(@RequestParam("X-Authenticated-User") Long id){

        System.out.println("i am prabhakar rana !!!");
        Long userId = id;
        List<Person> first_degree = connectionsService.getFirstDegree(userId);
        System.out.println("hii"+first_degree);
        System.out.println("hii"+userId);
        return ResponseEntity.ok(connectionsService.getFirstDegree(userId));
    }
}
