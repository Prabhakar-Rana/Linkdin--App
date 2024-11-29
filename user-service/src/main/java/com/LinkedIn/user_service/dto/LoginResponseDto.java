package com.LinkedIn.user_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class LoginResponseDto {
    private String accessToken;
}
