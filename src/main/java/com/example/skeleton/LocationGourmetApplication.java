package com.example.skeleton;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class LocationGourmetApplication {

    public static void main(String[] args) {
        SpringApplication.run(LocationGourmetApplication.class, args);
    }

}
