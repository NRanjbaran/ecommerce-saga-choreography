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
public class OrderFailedEvent extends BaseEvent {

    private String orderId;
    private String reason;
    private String failedStep;
}
