package com.saga.choreography.event.order;

import com.saga.choreography.event.BaseEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class OrderCancelledEvent extends BaseEvent {

    private String reason;
    private String customerEmail;
}
