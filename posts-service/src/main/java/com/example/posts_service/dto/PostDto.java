package com.example.posts_service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostDto  {
    private Long id;
    String content;
    Long userId;
    private LocalDateTime createdAt;
}
