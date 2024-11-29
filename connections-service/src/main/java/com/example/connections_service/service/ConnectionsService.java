package com.example.connections_service.service;

import com.example.connections_service.entity.Person;
import com.example.connections_service.repository.ConnectionsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ConnectionsService {
    private final ConnectionsRepository connectionsRepository;
    public List<Person> getFirstDegree(Long userId) {
        List<Person> first_degree = connectionsRepository.findByUserId(userId);
        return first_degree;
    }
}
