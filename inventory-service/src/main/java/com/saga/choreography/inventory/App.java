package com.saga.choreography.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        System.out.println("""
                \n=========================================================
                                INVENTORY SERVICE STARTED
                =========================================================
                Role: Saga participant - Manages stock and reservations
                Port: 8047
                Pattern: Choreography-based Saga
                =========================================================
                """);
    }
}
