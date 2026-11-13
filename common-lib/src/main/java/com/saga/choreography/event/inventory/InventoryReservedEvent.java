package com.saga.choreography.event.inventory;

import com.saga.choreography.dto.ReservedItem;
import com.saga.choreography.event.BaseEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class InventoryReservedEvent extends BaseEvent {

    private String reservationId;
    private String orderId;
    private List<ReservedItem> reservedItems;
    private ReservationStatus status;
    private LocalDateTime expiryTime;
}
