package com.example.posts_service.event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostCreatedEvent {
    private Long creatorId;
    private Long postId;
    private String content;
}
