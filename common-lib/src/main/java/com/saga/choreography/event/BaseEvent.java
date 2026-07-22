package com.saga.choreography.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseEvent implements Event {

    /**
     * Unique event identifier
     */
    @Builder.Default
    private final String eventId = UUID.randomUUID().toString();

    private String sagaId;
    private String correlationId;
    private String traceId;
    private String spanId;
    private String parentSpanId;
    private final LocalDateTime timestamp = LocalDateTime.now();
}
