package com.saga.choreography.event;

import com.saga.choreography.event.compensation.CompensationRequestedEvent;
import com.saga.choreography.event.inventory.InventoryFailedEvent;
import com.saga.choreography.event.inventory.InventoryReservedEvent;
import com.saga.choreography.event.order.OrderCancelledEvent;
import com.saga.choreography.event.order.OrderConfirmedEvent;
import com.saga.choreography.event.order.OrderCreatedEvent;
import com.saga.choreography.event.order.OrderFailedEvent;
import com.saga.choreography.event.payment.PaymentCompletedEvent;
import com.saga.choreography.event.payment.PaymentFailedEvent;
import com.saga.choreography.event.payment.PaymentRefundedEvent;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Canonical list of saga choreography event types.
 * <p>
 * Naming convention: {@code {SERVICE}_{ACTION}} / status suffix.
 * Message headers and Jackson polymorphic type ids use {@link #name()}.
 */
public enum EventType {

    // ========================================================================
    // ORDER SERVICE EVENTS
    // ========================================================================

    /**
     * Customer placed an order — payment starts processing.
     */
    ORDER_CREATED(OrderCreatedEvent.class),

    /**
     * All saga steps completed successfully.
     */
    ORDER_CONFIRMED(OrderConfirmedEvent.class),

    /**
     * Customer cancelled the order — compensation may follow.
     */
    ORDER_CANCELLED(OrderCancelledEvent.class),

    /**
     * Order failed (e.g. inventory or payment terminal failure).
     */
    ORDER_FAILED(OrderFailedEvent.class),

    // ========================================================================
    // PAYMENT SERVICE EVENTS
    // ========================================================================

    /**
     * Payment processing has started.
     */
    PAYMENT_PROCESSING(null),

    /**
     * Payment succeeded — inventory should reserve stock.
     */
    PAYMENT_COMPLETED(PaymentCompletedEvent.class),

    /**
     * Payment failed — order should fail / cancel.
     */
    PAYMENT_FAILED(PaymentFailedEvent.class),

    /**
     * Payment refunded during compensation.
     */
    PAYMENT_REFUNDED(PaymentRefundedEvent.class),

    // ========================================================================
    // INVENTORY SERVICE EVENTS
    // ========================================================================

    /**
     * Stock reserved — order can be confirmed.
     */
    INVENTORY_RESERVED(InventoryReservedEvent.class),

    /**
     * Insufficient stock — payment should compensate.
     */
    INVENTORY_FAILED(InventoryFailedEvent.class),

    // ========================================================================
    // COMPENSATION
    // ========================================================================

    /**
     * Ask a prior service to roll back its work.
     */
    COMPENSATION_REQUESTED(CompensationRequestedEvent.class);

    /**
     * Message header key used when publishing events.
     */
    public static final String HEADER = "eventType";

    private final Class<? extends BaseEvent> eventClass;

    EventType(Class<? extends BaseEvent> eventClass) {
        this.eventClass = eventClass;
    }

    private static final Map<Class<? extends BaseEvent>, EventType> BY_CLASS =
            Arrays.stream(values())
                    .filter(type -> type.eventClass != null)
                    .collect(Collectors.toUnmodifiableMap(type -> type.eventClass, Function.identity()));

    /**
     * Resolves the {@link EventType} for a concrete saga event instance.
     */
    public static EventType from(BaseEvent event) {
        if (event == null) {
            throw new IllegalArgumentException("event must not be null");
        }
        return from(event.getClass());
    }

    /**
     * Resolves the {@link EventType} for a concrete saga event class.
     */
    public static EventType from(Class<? extends BaseEvent> type) {
        return Optional.ofNullable(BY_CLASS.get(type))
                .orElseThrow(() -> new IllegalArgumentException("No EventType mapping for " + type.getName()));
    }
}