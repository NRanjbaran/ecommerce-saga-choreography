package com.saga.choreography.event.inventory;

import com.saga.choreography.event.BaseEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class InventoryFailedEvent extends BaseEvent {

    private String orderId;
    private String reason;
    private ReservationStatus reservationStatus;
    private Map<Long, Integer> failedItems;
}
