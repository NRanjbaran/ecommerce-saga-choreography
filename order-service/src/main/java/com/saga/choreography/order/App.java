package com.saga.choreography.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        System.out.println("""
                \n=========================================
                            ORDER SERVICE STARTED
                =========================================
                Role: Initiates the choreography saga
                Port: 8045
                =========================================
                """);
    }
}