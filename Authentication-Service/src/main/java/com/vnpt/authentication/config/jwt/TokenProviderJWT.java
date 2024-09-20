package com.vnpt.authentication.config.jwt;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenProviderJWT {
    @Value("${vnpt.app.jwtSecret}")
    private String JWT_SECRET;

    @Value("${vnpt.app.jwtExpirationMs}")
    private long JWT_EXPIRATION;

    @Value("${vnpt.app.jwtRefreshExpirationMs}")
    private long REFRESH_TOKEN_EXPIRATION;

    public String generateToken(String username) {
        var now = new Date();
        var expiryDate = new Date(now.getTime() + JWT_EXPIRATION);

        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
            .compact();
    }

    public String generateRefreshToken(String username) {
        var now = new Date();
        var expiryDate = new Date(now.getTime() + REFRESH_TOKEN_EXPIRATION);

        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
            .compact();
    }

    public String getUsernameFromJWT(String token) {
        var claims = Jwts.parser()
            .setSigningKey(JWT_SECRET)
            .parseClaimsJws(token)
            .getBody();

        return claims.getSubject();
    }


    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);

            return true;
        } catch (MalformedJwtException | ExpiredJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
            ex.printStackTrace();
        }

        return false;
    }
}
