package com.example.connections_service.repository;

import com.example.connections_service.entity.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConnectionsRepository extends Neo4jRepository<Person, Long> {

    @Query("MATCH (n: Person) -[:CONNECTED_TO]-" +
            " (m:Person)" +
            " WHERE n.id=$userId RETURN m")
    List<Person> findByUserId(Long userId);
}
