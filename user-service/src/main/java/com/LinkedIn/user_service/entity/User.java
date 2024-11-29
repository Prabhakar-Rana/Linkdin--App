package com.LinkedIn.user_service.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
@Entity
@Getter
@Setter
@Table(name = "app_user")
public class User {
    @Id  // Specifies the primary key of the entity
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-generates the ID
    private Long id;
    private String name;
    private String email;
    private String password;
    @CreationTimestamp
    private LocalDateTime createdAt;
}
