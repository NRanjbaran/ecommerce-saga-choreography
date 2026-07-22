package com.saga.choreography.event;

/**
 * ============================================================================
 * a list of all events in the saga
 * ============================================================================
 * <p>
 * Naming convention: {SERVICE}_{ACTION}_{STATUS}
 * - ORDER: Order service events
 * - PAYMENT: Payment service events
 * - INVENTORY: Inventory service events
 * - NOTIFICATION: Notification service events
 * <p>
 * Status types:
 * - REQUEST: Initial request (Example: PROCESS_PAYMENT_REQUEST)
 * - COMPLETED: Successful completion
 * - FAILED: Failed (triggers compensation)
 * - COMPENSATE: Rollback operation
 * <p>
 * ============================================================================
 */
public enum EventType {

    // ========================================================================
    // ORDER SERVICE EVENTS
    // ========================================================================
    /**
     * Initial event - customer places an order
     * Triggers: Payment service starts payment processing
     */
    ORDER_CREATED,

    /**
     * All steps completed successfully
     */
    ORDER_CONFIRMED,

    /**
     * Customer cancelled the order
     * Triggers: Compensation in all services
     */
    ORDER_CANCELLED,

    // ========================================================================
    // PAYMENT SERVICE EVENTS
    // ========================================================================
    /**
     * Payment service received ORDER_CREATED event
     * Indicates payment processing has started
     */
    PAYMENT_PROCESSING,

    /**
     * Payment was successful - charge customer
     * Triggers: Inventory service to reserve items
     */
    PAYMENT_COMPLETED,

    /**
     * Payment failed (insufficient funds, card declined)
     * Triggers: Compensation - order should be cancelled
     */
    PAYMENT_FAILED,

    /**
     * Payment was refunded (during compensation)
     */
    PAYMENT_REFUNDED,

    // ========================================================================
    // INVENTORY SERVICE EVENTS
    // ========================================================================

    /**
     * Items successfully reserved
     * Triggers: Order confirmation
     */
    INVENTORY_RESERVED,

    /**
     * Not enough stock available
     * Triggers: Payment refund compensation
     */
    INVENTORY_FAILED,

    // ========================================================================
    // COMPENSATION EVENTS (for rollback)
    // ========================================================================

    /**
     * General compensation request - service should rollback
     */
    COMPENSATION_REQUESTED

}