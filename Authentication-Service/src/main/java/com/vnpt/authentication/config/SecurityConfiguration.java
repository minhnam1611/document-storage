package com.vnpt.authentication.config;

import com.vnpt.authentication.security.oauth2.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.http.OAuth2ErrorResponseErrorHandler;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.endpoint.DefaultMapOAuth2AccessTokenResponseConverter;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.core.http.converter.OAuth2AccessTokenResponseHttpMessageConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfiguration {

    @Autowired
    private CorsConfigurationSource corsConfigurationSource;

    @Autowired
    private CustomOidcUserService customOidcUserService;

    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;

    @Autowired
    private OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

    @Autowired
    private OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

    public SecurityConfiguration() {
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.configurationSource(corsConfigurationSource))
            .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
                authorizationManagerRequestMatcherRegistry.requestMatchers(mvc.pattern("/api/**")).permitAll();
            });
//            .authorizeHttpRequests(
//                authz ->
//                    // prettier-ignore
//                authz
//                    .requestMatchers(mvc.pattern(HttpMethod.POST, "/api/authenticate")).permitAll()
//                    .requestMatchers(mvc.pattern(HttpMethod.GET, "/api/authenticate")).permitAll()
//                    .requestMatchers(mvc.pattern("/api/admin/**")).hasAuthority(AuthoritiesConstants.ADMIN)
//                    .requestMatchers(mvc.pattern("/api/**")).permitAll()
//                    .requestMatchers(mvc.pattern("/v3/api-docs/**")).hasAuthority(AuthoritiesConstants.ADMIN)
//                    .requestMatchers(mvc.pattern("/management/health")).permitAll()
//                    .requestMatchers(mvc.pattern("/management/health/**")).permitAll()
//                    .requestMatchers(mvc.pattern("/management/info")).permitAll()
//                    .requestMatchers(mvc.pattern("/management/prometheus")).permitAll()
//                    .requestMatchers(mvc.pattern("/management/**")).hasAuthority(AuthoritiesConstants.ADMIN)
//            )
//            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//            .exceptionHandling(
//                exceptions ->
//                    exceptions
//                        .authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint())
//                        .accessDeniedHandler(new BearerTokenAccessDeniedHandler())
//            )
//            .oauth2ResourceServer(oauth2 -> oauth2.jwt(withDefaults()));
        http.oauth2Login(oauth2 ->
                oauth2
                    .authorizationEndpoint(authorization -> authorization.authorizationRequestRepository(cookieAuthorizationRequestRepository()))
//                .redirectionEndpoint(redirection -> redirection)
                    .userInfoEndpoint(userInfo -> userInfo
                        .oidcUserService(customOidcUserService)
                        .userService(customOAuth2UserService)
                    )
//                .tokenEndpoint(token -> token.accessTokenResponseClient(authorizationCodeTokenResponseClient()))
                    .successHandler(oAuth2AuthenticationSuccessHandler)
                    .failureHandler(oAuth2AuthenticationFailureHandler)

        );
        return http.build();
    }

    @Bean
    MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
        return new MvcRequestMatcher.Builder(introspector);
    }

    @Bean
    public HttpCookieOAuth2AuthorizationRequestRepository cookieAuthorizationRequestRepository() {
        return new HttpCookieOAuth2AuthorizationRequestRepository();
    }

    private OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> authorizationCodeTokenResponseClient() {
        OAuth2AccessTokenResponseHttpMessageConverter tokenResponseHttpMessageConverter = new OAuth2AccessTokenResponseHttpMessageConverter();

        var defaultMapConverter = new DefaultMapOAuth2AccessTokenResponseConverter();
        Converter<Map<String, Object>, OAuth2AccessTokenResponse> linkedinMapConverter = tokenResponse -> {
            var withTokenType = new HashMap<>(tokenResponse);
            withTokenType.put(OAuth2ParameterNames.TOKEN_TYPE, OAuth2AccessToken.TokenType.BEARER.getValue());
            return defaultMapConverter.convert(withTokenType);
        };


        tokenResponseHttpMessageConverter.setAccessTokenResponseConverter(linkedinMapConverter);

        RestTemplate restTemplate = new RestTemplate(Arrays.asList(new FormHttpMessageConverter(), tokenResponseHttpMessageConverter));
        restTemplate.setErrorHandler(new OAuth2ErrorResponseErrorHandler());
        DefaultAuthorizationCodeTokenResponseClient tokenResponseClient = new DefaultAuthorizationCodeTokenResponseClient();
        tokenResponseClient.setRestOperations(restTemplate);
        return tokenResponseClient;
    }
}
