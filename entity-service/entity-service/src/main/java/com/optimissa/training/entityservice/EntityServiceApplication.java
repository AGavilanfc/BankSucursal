package com.optimissa.training.entityservice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class EntityServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(EntityServiceApplication.class, args);
    }
}
