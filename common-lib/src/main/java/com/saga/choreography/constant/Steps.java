package com.saga.choreography.constant;

import lombok.Getter;

@Getter
public enum Steps {

    INVENTORY_SERVICE("inventory-service"),
    NOTIFICATION_SERVICE("notification-service"),
    ORDER_SERVICE("order-service"),
    PAYMENT_SERVICE("payment-service"),

    DATABASE("database"),
    RABBITMQ("rabbitmq"),
    REDIS("redis"),
    MONGODB("MONGODB"),

    SMTP("SMTP"),
    SMS_PROVIDER("SMS_PROVIDER"),
    STRIPE("STRIPE"),
    PAYPAL("PAYPAL"),


    ;

    private final String value;

    Steps(String value) {
        this.value = value;
    }

}
