package com.saga.choreography.event.compensation;

import com.saga.choreography.event.BaseEvent;
import com.saga.choreography.event.EventType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class CompensationRequestedEvent extends BaseEvent {

    private String reason;
    private EventType originalEventType;
    private String compensateService;
    private String sourceService;
}
