package com.vnpt.registryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class RegistryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegistryServiceApplication.class, args);
    }

}
