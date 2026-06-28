package com.textile.erp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class TextileErpApplication {

    public static void main(String[] args) {
        SpringApplication.run(TextileErpApplication.class, args);
    }
}