package com.optimissa.training.countryservice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class CountryServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CountryServiceApplication.class, args);

    }
}
