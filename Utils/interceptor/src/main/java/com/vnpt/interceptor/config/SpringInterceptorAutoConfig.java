package com.vnpt.interceptor.config;

import com.vnpt.interceptor.PermissionInterceptor;
import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ConfigurationProperties(prefix = "ths.interceptor")
public class SpringInterceptorAutoConfig {
    private String url = "http://localhost:3100/loki/api/v1/push";

    @Value("${spring.application.name:-}")
    String name;

    @Value("${ths.interceptor.auth-service-url:http://localhost:8084}")
    String authServiceUrl;


    @Value("${ths.interceptor.email-service-url:http://localhost:8081}")
    String emailServiceUrl;
    @Bean
    public FeignUserClient feignUserClient() {
        return new Feign.Builder().client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logLevel(Logger.Level.FULL).target(FeignUserClient.class, authServiceUrl);
    }

    @Bean
    public FeignEmailClient feignEmailClient(){
        return new Feign.Builder().client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logLevel(Logger.Level.FULL).target(FeignEmailClient.class, emailServiceUrl);
    }

    @Bean
    public PermissionInterceptor permissionInterceptor(FeignUserClient feignUserClient, RedisTemplate<String, Object> redisTemplate) {
        return new PermissionInterceptor(feignUserClient, redisTemplate);
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer(PermissionInterceptor permissionInterceptor) {
        return new RequestInterceptorConfig(permissionInterceptor);
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new JedisConnectionFactory();
    }
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }

}
