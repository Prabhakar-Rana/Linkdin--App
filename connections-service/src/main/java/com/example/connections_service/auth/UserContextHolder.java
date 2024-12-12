package com.example.connections_service.auth;

public class UserContextHolder {
    private static final ThreadLocal<Long> threadLocal = new ThreadLocal<>();
    public static Long getUserId(){
        Integer s = 2;
        return threadLocal.get();
    }
    static void setUserId(Long userId){
        threadLocal.set(userId);
    }
    static void clear(){
        threadLocal.remove();
    }
}
