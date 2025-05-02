package com.example.Mini_assessment_userService.utils;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import io.jsonwebtoken.Claims;
import javax.crypto.SecretKey;
import java.util.Date;

public class jwtUtils {
    @Value("${spring.secret.key}")
    private String SECRET_KEY;

    private SecretKey getSignkey(){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }
    public String extractEmail(String token) {
        return extractAllClaims(token).getSubject();
    }

    public Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }

    public boolean validateToken(String token) {
        try {
            return !isTokenExpired(token);
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSignkey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
