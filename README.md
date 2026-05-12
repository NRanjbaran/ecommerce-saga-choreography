# ecommerce-saga-choreography

## A Learning Project for Microservices with Event-Driven Architecture

This project shows you how multiple microservices work together to process an e-commerce order (in success/failed scenarios). 
Instead of one big program, I have 4 small services (as a separate service module) that talk to each other using events (messages).
E-commerce microservices using Choreography-based Saga pattern - services communicate via events without central coordinator. each service knows its part and reacts to the events.

## Design Philosophy: Why This Project is Built This Way

When I started this project, I wanted to use how to build a system where multiple services work together without breaking. Here are the key decisions I made and why.
