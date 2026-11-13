package com.saga.choreography.event;

import com.saga.choreography.event.inventory.InventoryFailedEvent;
import com.saga.choreography.event.order.OrderCreatedEvent;
import com.saga.choreography.event.payment.PaymentCompletedEvent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("EventType")
class EventTypeTest {

    @Test
    @DisplayName("maps concrete event classes to enum constants")
    void mapsKnownEventClasses() {
        assertThat(EventType.from(OrderCreatedEvent.builder().build())).isEqualTo(EventType.ORDER_CREATED);
        assertThat(EventType.from(PaymentCompletedEvent.builder().build())).isEqualTo(EventType.PAYMENT_COMPLETED);
        assertThat(EventType.from(InventoryFailedEvent.builder().build())).isEqualTo(EventType.INVENTORY_FAILED);
    }

    @Test
    @DisplayName("header constant is eventType")
    void headerName() {
        assertThat(EventType.HEADER).isEqualTo("eventType");
    }

    @Test
    @DisplayName("rejects null events")
    void rejectsNull() {
        assertThatThrownBy(() -> EventType.from((BaseEvent) null))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
