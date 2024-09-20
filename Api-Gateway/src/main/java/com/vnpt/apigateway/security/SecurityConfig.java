package com.vnpt.apigateway.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfig {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private SecurityContextRepository securityContextRepository;


    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Autowired
    private CustomServerAccessDeniedHandler accessDeniedHandler;
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
        final CorsConfigurationSource configurationSource = serverWebExchange -> {
            final var cc = new CorsConfiguration();
            cc.addAllowedOrigin("*");
            cc.addAllowedMethod("*");
            cc.addAllowedHeader("*");
            return cc;
        };

        Customizer<ServerHttpSecurity.CorsSpec> corsCustomizer = (corsSpec) -> corsSpec.configurationSource(configurationSource);


        httpSecurity.csrf(ServerHttpSecurity.CsrfSpec::disable)
                .cors(corsCustomizer)
                .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
                .exceptionHandling(ex -> ex.accessDeniedHandler(accessDeniedHandler))
                .securityContextRepository(securityContextRepository)
                .authenticationManager(authenticationManager)

//                .authorizeExchange(exchanges ->
//                        exchanges
//                                .pathMatchers("/auth-service/api/auth/**")
//                                .permitAll()
//                                .pathMatchers(HttpMethod.GET, EndPointConstants.AUTH_USER + "/**")
//                                .hasAnyAuthority(UserPermission.USERS_SEARCH.key)
//                                .anyExchange().authenticated()
//                )
        ;

        return httpSecurity.build();
    }
}
