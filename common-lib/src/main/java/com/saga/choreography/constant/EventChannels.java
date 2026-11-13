package com.saga.choreography.constant;

/**
 * Message broker destinations used by the choreography saga.
 * All services publish and subscribe on the shared saga events topic.
 */
public final class EventChannels {

    private EventChannels() {}

    /**
     * Shared destination for all saga events (choreography bus).
     * Used by StreamBridge publishers and Cloud Stream consumer bindings.
     */
    public static final String SAGA_EVENTS = "saga.events.topic";

    /** Dead letter queue for events that failed after all retries */
    public static final String DEAD_LETTER_QUEUE = "saga.dead.letter";
}
