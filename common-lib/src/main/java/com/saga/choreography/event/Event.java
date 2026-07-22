package com.saga.choreography.event;

import java.time.LocalDateTime;

public interface Event {

    String getEventId();

    String getCorrelationId();

    LocalDateTime getTimestamp();

}
