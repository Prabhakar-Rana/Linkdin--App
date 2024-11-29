package com.example.api_gateway;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
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
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(getSecretKey()) // Replace with your key retrieval logic
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String var = claims.get("userId", String.class);// Remove in production
            return claims.getSubject();
        } catch (SignatureException ex) {
            System.err.println("Invalid JWT signature: " + ex.getMessage());
        } catch (MalformedJwtException ex) {
            System.err.println("Malformed JWT: " + ex.getMessage());
        } catch (ExpiredJwtException ex) {
            System.err.println("JWT has expired: " + ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            System.err.println("Unsupported JWT: " + ex.getMessage());
        } catch (IllegalArgumentException ex) {
            System.err.println("Illegal argument in JWT parsing: " + ex.getMessage());
        }
        return null; // Or throw a custom exception
    }

}
