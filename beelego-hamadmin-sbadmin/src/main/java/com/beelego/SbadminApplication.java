package com.beelego;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer
public class SbadminApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbadminApplication.class, args);
    }
}
