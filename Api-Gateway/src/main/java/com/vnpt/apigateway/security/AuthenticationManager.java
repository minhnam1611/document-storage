package com.vnpt.apigateway.security;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class AuthenticationManager implements ReactiveAuthenticationManager {

    @Value("${vnpt.app.jwtSecret}")
    private String jwtSecret;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String authToken = authentication.getCredentials().toString();
//        String username = JwtUtil.getUsernameFromToken(authToken);
        return Mono.just(JwtUtil.validateJwtToken(authToken, jwtSecret))
                .filter(valid -> valid)
                .switchIfEmpty(Mono.empty())
                .map(jwt -> {
                    Claims claims = JwtUtil.getAllClaimsFromToken(authToken, jwtSecret);
                    List<String> rolesMap = claims.get("authorities", ArrayList.class);
                    if (rolesMap == null) rolesMap = new ArrayList<>();

                    List<GrantedAuthority> list = rolesMap.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
                    return new UsernamePasswordAuthenticationToken(
                            claims.get("sub", String.class),
                            null,
                            list
                    );
                });
    }
}
