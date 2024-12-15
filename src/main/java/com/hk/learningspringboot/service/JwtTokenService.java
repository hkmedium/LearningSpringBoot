package com.hk.learningspringboot.service;

import com.hk.learningspringboot.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

@Service
public class JwtTokenService {
    @Value("${security.jwt.secret-key}")
    private String secretKey;
    @Value("${security.jwt.access-token-expiration}")
    private long accessTokenExpire;
    @Value("${security.jwt.refresh-token-expiration}")
    private long refreshTokenExpire;

    public String generateAccessToken(String username, Set<String> roles) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", roles);

        return Jwts.builder()
                .setClaims(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + accessTokenExpire))
                //.signWith(SignatureAlgorithm.HS256, secretKey)
                .signWith(getSigninKey())
                .compact();
    }

    public String generateAccessToken(User user) {
        System.out.println("JwtTokenService generateAccessToken ::: >>>  Roll: " + user.getRole().name());
        return Jwts.builder()
                .subject(user.getUsername())
                .claim("roles", user.getRole().name().toString())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + accessTokenExpire))
                //.signWith(SignatureAlgorithm.HS512, secretKey)
                .signWith(getSigninKey())
                .compact();
    }

    public String generateRefreshToken(String username) {
        String token = Jwts.builder()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + refreshTokenExpire))
                //.signWith(SignatureAlgorithm.HS256, secretKey)
                .signWith(getSigninKey())
                .compact();
        return token;
    }

    public String generateRefreshToken(User user) {
        String token = Jwts.builder()
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + refreshTokenExpire))
                //.signWith(SignatureAlgorithm.HS512, secretKey)
                .signWith(getSigninKey())
                .compact();
        return token;
    }

    public boolean isValidAccessToken(String token, String username) {
        String tokenUsername = getUsernameFromToken(token);
        return (username.equals(tokenUsername) && !isTokenExpired(token));
    }

    public boolean isValidRefreshToken(String token, String username) {
        String tokenUsername = getUsernameFromToken(token);
        return (username.equals(tokenUsername)) && !isTokenExpired(token);
    }

    public String getUsernameFromToken(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSigninKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSigninKey() {
        byte[] keyBytes = Decoders.BASE64URL.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
