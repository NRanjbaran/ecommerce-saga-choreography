package com.saga.choreography.constant;

public final class SagaTopics {
    public static final String ORDER_CREATED = "order.events.created";
    public static final String PAYMENT_COMPLETED = "payment.events.completed";
    public static final String PAYMENT_FAILED = "payment.events.failed";
    public static final String PAYMENT_REFUNDED = "payment.events.refunded";
    public static final String INVENTORY_RESERVED = "inventory.events.reserved";
    public static final String INVENTORY_FAILED = "inventory.events.failed";
    public static final String ORDER_SHIPPED = "order.events.shipped";

    private SagaTopics() {
    }
}
