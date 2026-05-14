package com.saga.choreography.util;

import org.slf4j.MDC;

import java.util.UUID;

/**
 * Distributed tracing across services
 * <p>
 * Each request gets a unique trace ID that flows through all services
 * This allows correlating logs across different services
 * <p>
 * Usage:
 * - TraceIdUtil.generateTraceId() at the start of a request
 * - Include traceId in all events and logs
 * - Use MDC to automatically add traceId to log statements
 * <p>
 */
public final class TraceIdUtil {

    private static final String TRACE_ID_KEY = "traceId";

    private TraceIdUtil() {
    }

    /**
     * Generate a new unique trace ID (UUID format)
     */
    public static String generateTraceId() {
        return UUID.randomUUID().toString();
    }

    /**
     * Get current trace ID from MDC (Mapped Diagnostic Context)
     */
    public static String getCurrentTraceId() {
        String traceId = MDC.get(TRACE_ID_KEY);
        if (traceId == null) {
            traceId = generateTraceId();
            setCurrentTraceId(traceId);
        }
        return traceId;
    }

    /**
     * Set trace ID in MDC for current thread
     */
    public static void setCurrentTraceId(String traceId) {
        if (traceId != null) {
            MDC.put(TRACE_ID_KEY, traceId);
        }
    }

    /**
     * Clear trace ID from MDC (when request completes)
     */
    public static void clearTraceId() {
        MDC.remove(TRACE_ID_KEY);
    }

    /**
     * Create a trace ID from existing saga ID
     * Ensures all events in a saga share the same trace ID
     */
    public static String fromSagaId(String sagaId) {
        if (sagaId == null) {
            return generateTraceId();
        }
        return sagaId;
    }
}