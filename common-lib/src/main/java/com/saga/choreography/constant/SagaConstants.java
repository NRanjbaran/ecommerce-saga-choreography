package com.saga.choreography.constant;

import com.saga.choreography.event.EventType;

/**
 * ============================================================================
 * Configuration values used across all services
 * ============================================================================
 *
 * Centralized constants for consistent behavior across all services
 *
 * ============================================================================
 */
public final class SagaConstants {

    private SagaConstants() {}  // Prevent instantiation

    // ========================================================================
    // SAGA TIMEOUTS (in milliseconds)
    // ========================================================================

    /** Maximum time for an entire saga to complete */
    public static final long SAGA_TOTAL_TIMEOUT_MS = 30000;  // 30 seconds

    /** Time to wait for each step to complete */
    public static final long SAGA_STEP_TIMEOUT_MS = 10000;   // 10 seconds

    /** Time to wait for compensation to complete */
    public static final long SAGA_COMPENSATION_TIMEOUT_MS = 15000;  // 15 seconds

    // ========================================================================
    // RETRY CONFIGURATION
    // ========================================================================

    /** Maximum number of retry attempts for failed operations */
    public static final int MAX_RETRY_ATTEMPTS = 3;

    /** Initial delay between retries (milliseconds) */
    public static final long RETRY_INITIAL_DELAY_MS = 1000;   // 1 second

    /** Maximum delay between retries */
    public static final long RETRY_MAX_DELAY_MS = 10000;      // 10 seconds

    /** Delay multiplier (exponential backoff) */
    public static final double RETRY_MULTIPLIER = 2.0;

    // ========================================================================
    // INVENTORY CONFIGURATION
    // ========================================================================

    /** How long to hold a reservation (milliseconds) */
    public static final long RESERVATION_TIMEOUT_MS = 600000;  // 10 minutes

    /** Minimum stock level before alerting */
    public static final int LOW_STOCK_THRESHOLD = 10;

    /** Critical stock level requiring immediate action */
    public static final int CRITICAL_STOCK_THRESHOLD = 5;

    // ========================================================================
    // ORDER STATUS VALUES
    // ========================================================================

    public static final String ORDER_STATUS_PENDING = "PENDING";
    public static final String ORDER_STATUS_PROCESSING = "PROCESSING";
    public static final String ORDER_STATUS_CONFIRMED = "CONFIRMED";
    public static final String ORDER_STATUS_CANCELLED = "CANCELLED";
    public static final String ORDER_STATUS_FAILED = "FAILED";
    public static final String ORDER_STATUS_COMPLETED = "COMPLETED";

    // ========================================================================
    // PAYMENT STATUS VALUES
    // ========================================================================

    public static final String PAYMENT_STATUS_PENDING = "PENDING";
    public static final String PAYMENT_STATUS_PROCESSING = "PROCESSING";
    public static final String PAYMENT_STATUS_COMPLETED = "COMPLETED";
    public static final String PAYMENT_STATUS_FAILED = "FAILED";
    public static final String PAYMENT_STATUS_REFUNDED = "REFUNDED";

    // ========================================================================
    // INVENTORY STATUS VALUES
    // ========================================================================

    public static final String INVENTORY_STATUS_RESERVED = "RESERVED";
    public static final String INVENTORY_STATUS_RELEASED = "RELEASED";
    public static final String INVENTORY_STATUS_FAILED = "FAILED";

    // ========================================================================
    // NOTIFICATION TYPES (aligned with EventType where applicable)
    // ========================================================================

    public static final String NOTIFICATION_TYPE_ORDER_CONFIRMATION = EventType.ORDER_CONFIRMED.name();
    public static final String NOTIFICATION_TYPE_PAYMENT_RECEIPT = EventType.PAYMENT_COMPLETED.name();
    public static final String NOTIFICATION_TYPE_ORDER_CANCELLED = EventType.ORDER_CANCELLED.name();
    public static final String NOTIFICATION_TYPE_PAYMENT_FAILED = EventType.PAYMENT_FAILED.name();
}
