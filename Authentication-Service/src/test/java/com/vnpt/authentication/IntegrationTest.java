package com.vnpt.authentication;

import com.vnpt.authentication.config.AsyncSyncConfiguration;
import com.vnpt.authentication.config.EmbeddedRedis;
import com.vnpt.authentication.config.EmbeddedSQL;
import com.vnpt.authentication.config.JacksonConfiguration;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Base composite annotation for integration tests.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest(classes = { AuthenticationServiceApp.class, JacksonConfiguration.class, AsyncSyncConfiguration.class })
@EmbeddedRedis
@EmbeddedSQL
public @interface IntegrationTest {
}
