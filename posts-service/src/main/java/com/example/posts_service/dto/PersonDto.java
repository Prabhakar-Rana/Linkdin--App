package com.example.posts_service.dto;

import lombok.Data;

@Data
public class PersonDto {
    private Long id;
    private Long userId;
    private String name;
}