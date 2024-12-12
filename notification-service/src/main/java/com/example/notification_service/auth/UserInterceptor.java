package com.example.notification_service.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class UserInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userId = request.getHeader("X-Authenticated-User");
        if(userId!=null){
            UserContextHolder.setUserId(Long.valueOf(userId));
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    public void afterCompletion(){
        UserContextHolder.clear();
    }
}
