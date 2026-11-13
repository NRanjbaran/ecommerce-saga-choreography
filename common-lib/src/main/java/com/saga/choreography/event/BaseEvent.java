package com.saga.choreography.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Base type for all saga events. Jackson polymorphism enables a shared topic
 * where every service deserializes the correct concrete event class.
 * <p>
 * {@code @JsonSubTypes} type names must match {@link EventType#name()}.
 */
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "eventType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = OrderCreatedEvent.class, name = "ORDER_CREATED"),
        @JsonSubTypes.Type(value = OrderConfirmedEvent.class, name = "ORDER_CONFIRMED"),
        @JsonSubTypes.Type(value = OrderCancelledEvent.class, name = "ORDER_CANCELLED"),
        @JsonSubTypes.Type(value = OrderFailedEvent.class, name = "ORDER_FAILED"),
        @JsonSubTypes.Type(value = PaymentCompletedEvent.class, name = "PAYMENT_COMPLETED"),
        @JsonSubTypes.Type(value = PaymentFailedEvent.class, name = "PAYMENT_FAILED"),
        @JsonSubTypes.Type(value = PaymentRefundedEvent.class, name = "PAYMENT_REFUNDED"),
        @JsonSubTypes.Type(value = InventoryReservedEvent.class, name = "INVENTORY_RESERVED"),
        @JsonSubTypes.Type(value = InventoryFailedEvent.class, name = "INVENTORY_FAILED"),
        @JsonSubTypes.Type(value = CompensationRequestedEvent.class, name = "COMPENSATION_REQUESTED")
})
public abstract class BaseEvent implements Event {

    @Builder.Default
    private String eventId = UUID.randomUUID().toString();

    private String sagaId;
    private String correlationId;
    private String traceId;
    private String spanId;
    private String parentSpanId;

    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();
}
