package com.LinkedIn.posts_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Post {
    @Id  // Specifies the primary key of the entity
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-generates the ID
    private Long id;
    String content;
    Long userId;
    @CreationTimestamp
    private LocalDateTime createdAt;
}
