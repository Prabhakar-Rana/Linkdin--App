package com.example.notification_service.auth;

public class UserContextHolder {
    private static final ThreadLocal<Long> threadLocal = new ThreadLocal<>();
    public static Long getUserId(){
        return threadLocal.get();
    }
    public static void setUserId(Long userId){
        threadLocal.set(userId);
    }
    static void clear(){
        threadLocal.remove();
    }
}
