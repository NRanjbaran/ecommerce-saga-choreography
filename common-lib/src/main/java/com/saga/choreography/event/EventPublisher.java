package com.saga.choreography.event;

/**
 * Contract for publishing saga events onto the shared choreography bus.
 */
public interface EventPublisher {

    boolean publish(BaseEvent event);
}
