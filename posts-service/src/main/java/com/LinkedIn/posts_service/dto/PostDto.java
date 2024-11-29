package com.LinkedIn.posts_service.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostDto  {
    private Long id;
    String content;
    Long userId;
    private LocalDateTime createdAt;
}
