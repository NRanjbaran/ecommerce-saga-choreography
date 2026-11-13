package com.saga.choreography.event.order;

public enum CancellationType {
    CUSTOMER_INITIATED,
    PAYMENT_FAILED,
    INVENTORY_FAILED,
    SHIPPING_FAILED,
    SYSTEM_ERROR
}
