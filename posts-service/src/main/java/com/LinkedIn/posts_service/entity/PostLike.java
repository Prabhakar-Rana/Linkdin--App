package com.LinkedIn.posts_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostLike {
    @Id  // Specifies the primary key of the entity
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-generates the ID
    private Long id;
    Long postId;
    Long userId;
    @CreationTimestamp
    private LocalDateTime createdAt;
}
