package com.vnpt.apigateway.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Key;


@Slf4j
public class JwtUtil {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    public static Boolean validateJwtToken(String authToken, String jwtSecret) {
        try {
            Jwts.parserBuilder().setSigningKey(key(jwtSecret)).build().parse(authToken);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        } catch (Exception e) {
            logger.error("ValidateJwtToken error: {}", e.getMessage());
        }

        return false;
    }

    public static Claims getAllClaimsFromToken(String token, String jwtSecret) {
        return Jwts.parserBuilder().setSigningKey(key(jwtSecret)).build().parseClaimsJws(token).getBody();
    }

    private static Key key(String jwtSecret) {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }
}
