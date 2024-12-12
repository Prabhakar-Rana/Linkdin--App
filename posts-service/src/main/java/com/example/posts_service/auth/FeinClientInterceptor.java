package com.example.posts_service.auth;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

@Component
public class FeinClientInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        Long userId = UserContextHolder.getUserId();
        if(userId!=null){
            requestTemplate.header("X-Authenticated-User", userId.toString());
        }
    }
}
