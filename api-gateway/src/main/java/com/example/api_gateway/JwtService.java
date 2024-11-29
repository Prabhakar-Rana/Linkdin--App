package com.example.api_gateway;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Service
public class JwtService {
    @Value("${jwt.secretKey}")
    private String jwtSecretKey;

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
    }

    //    public Long getUserIdFromToken(String token) {
//        Claims claims = Jwts.parserBuilder()
//                .setSigningKey(getSecretKey())  // Use setSigningKey to verify the token
//                .build()
//                .parseClaimsJws(token)          // Parse the token
//                .getBody();                     // Extract the claims from the token
//
//        return Long.valueOf(claims.getSubject());  // Get the subject (user ID) from the claims
//    }
    public String getUserIdFromToken(String token) {
       // try{
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build().parseClaimsJws(token).getBody();
            System.out.println("temp="+claims);
            return "val--";
//        } catch (JwtException e){
//            System.out.println("Invalid JWT: " + e.getMessage());
//            return "hello";
//        }

    }
}
