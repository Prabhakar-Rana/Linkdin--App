package com.example.api_gateway.filters;

import com.example.api_gateway.JwtService;
import io.jsonwebtoken.JwtException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Slf4j
@Component

public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
    private final JwtService jwtService;
    public AuthenticationFilter(JwtService jwtService) {
        super(Config.class); // Use a Config class for customization if needed
        this.jwtService = jwtService;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            HttpHeaders headers = exchange.getRequest().getHeaders();
            String authHeader = headers.getFirst(HttpHeaders.AUTHORIZATION);

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                // Reject the request
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            // Optionally validate the token (e.g., using JWT)
            String token = authHeader.substring(7);
            String userId = jwtService.getUserIdFromToken(token);
            System.out.println("userId1="+userId);// Strip "Bearer " prefix
            try{
                String userI = jwtService.getUserIdFromToken(token);
                exchange.getRequest().mutate()
                        .header("X-Authenticated-User", userI)
                        .build();
                // Proceed with the filter chain if valid
                System.out.println("i am prabhakar !");
                return chain.filter(exchange);
            } catch (JwtException e){
                log.error("JWT Exception: {}", e.getLocalizedMessage());
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

        };
    }

    public static class Config {
        // Define configuration properties if needed
    }
}
