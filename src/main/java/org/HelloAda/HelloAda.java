package org.HelloAda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@ComponentScan(basePackages = "org.HelloAda")
public class HelloAda {
    public static void main(String[] args) {
        SpringApplication.run(HelloAda.class, args);
    }
}