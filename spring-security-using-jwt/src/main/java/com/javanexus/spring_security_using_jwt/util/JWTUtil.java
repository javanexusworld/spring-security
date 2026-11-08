package com.javanexus.spring_security_using_jwt.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JWTUtil {

    @Value("${jwt.expiration}")
    private Long expirationTime;

    @Value("${jwt.secret}")
    private String SECRET;

    private SecretKey key;

    @PostConstruct
    public void init() {
        key = Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public String genarateJWTToken(String userName) {
        return Jwts.builder()
                .subject(userName)
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .issuedAt(new Date())
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String getUserName(String token) {
        return getClaims(token)
                .getSubject();

    }

    public boolean isTokenValid(String userNanme, UserDetails userDetails, String token) {
        return userNanme.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private Claims getClaims(String token) {
        return Jwts.parser().verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

}
