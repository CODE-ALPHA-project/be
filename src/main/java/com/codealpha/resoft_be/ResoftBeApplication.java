package com.codealpha.resoft_be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class ResoftBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResoftBeApplication.class, args);
    }

}
