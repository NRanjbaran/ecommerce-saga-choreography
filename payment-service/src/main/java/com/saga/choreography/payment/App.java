package com.saga.choreography.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        System.out.println("""
                \n=========================================
                            PAYMENT SERVICE STARTED
                =========================================
                Role: Processes payments for orders
                Port: 8046
                =========================================
                """);
    }
}