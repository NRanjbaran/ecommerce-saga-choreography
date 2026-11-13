package com.saga.choreography.util;

import io.micrometer.tracing.TraceContext;
import io.micrometer.tracing.Tracer;
import jakarta.annotation.PreDestroy;
import org.slf4j.MDC;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

/**
 * Distributed tracing helpers. Propagates trace IDs via MDC across saga steps.
 */
@Component
public class TraceIdUtil {

    private static final String TRACE_ID_KEY = "traceId";
    private static final String SPAN_ID_KEY = "spanId";
    private static final String PARENT_SPAN_ID_KEY = "parentSpanId";

    private final Tracer tracer;

    public TraceIdUtil(ObjectProvider<Tracer> tracerProvider) {
        this.tracer = tracerProvider.getIfAvailable();
    }

    public String getCurrentTraceId() {
        TraceContext context = getTraceContext();

        if (context != null) {
            String traceId = context.traceId();
            MDC.put(TRACE_ID_KEY, traceId);
            MDC.put(SPAN_ID_KEY, context.spanId());
            Optional.ofNullable(context.parentId())
                    .ifPresent(parentId -> MDC.put(PARENT_SPAN_ID_KEY, parentId));
            return traceId;
        }

        String fallbackTraceId = generateRandomTraceId();
        setCurrentTraceId(fallbackTraceId);
        return fallbackTraceId;
    }

    public String getCurrentSpanId() {
        TraceContext context = getTraceContext();
        return context != null ? context.spanId() : UUID.randomUUID().toString().replace("-", "").substring(0, 16);
    }

    public String getCurrentParentSpanId() {
        TraceContext context = getTraceContext();
        return context != null ? context.parentId() : null;
    }

    public void setCurrentTraceId(String traceId) {
        if (traceId != null) {
            MDC.put(TRACE_ID_KEY, traceId);
        }
    }

    public String getOrCreateTraceId(String existingTraceId) {
        if (existingTraceId != null && !existingTraceId.isEmpty()) {
            MDC.put(TRACE_ID_KEY, existingTraceId);
            return existingTraceId;
        }
        return getCurrentTraceId();
    }

    public void clearMDC() {
        MDC.remove(TRACE_ID_KEY);
        MDC.remove(SPAN_ID_KEY);
        MDC.remove(PARENT_SPAN_ID_KEY);
    }

    private String generateRandomTraceId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    private TraceContext getTraceContext() {
        if (tracer == null) {
            return null;
        }
        return tracer.currentTraceContext().context();
    }

    @PreDestroy
    public void cleanup() {
        clearMDC();
    }
}
