package com.vnpt.authentication.security.jwt;

import com.vnpt.authentication.config.SecurityConfiguration;
//import com.vnpt.authentication.config.SecurityJwtConfiguration;
import com.vnpt.authentication.config.WebConfigurer;
import com.vnpt.authentication.management.SecurityMetersService;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.boot.test.context.SpringBootTest;
import tech.jhipster.config.JHipsterProperties;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest(
    classes = {
        JHipsterProperties.class,
        WebConfigurer.class,
        SecurityConfiguration.class,
//        SecurityJwtConfiguration.class,
        SecurityMetersService.class,
        JwtAuthenticationTestUtils.class,
    }
)
public @interface AuthenticationIntegrationTest {
}
