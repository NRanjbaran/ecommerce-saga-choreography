package com.saga.choreography.exception;

import com.saga.choreography.constant.Steps;
import lombok.Getter;
import lombok.Setter;

/**
 * Base exception for all saga-related errors
 * <p>
 * When this exception is thrown, it triggers compensation
 * The service should rollback its operations and publish a FAILED event
 */
@Getter
public class SagaException extends RuntimeException {

    /**
     * The saga ID (correlation ID)
     */
    private final String sagaId;

    /**
     * The order ID
     */
    private final String orderId;

    /**
     * Which step/system in the saga failed (Example: "PAYMENT", "INVENTORY")
     */
    protected final Steps failedStep;

    private String errorCode;
    private int httpStatus;
    private boolean retryable;
    private Throwable originalException;

    /**
     * Whether compensation has already been triggered
     */
    @Getter
    @Setter
    private boolean compensationTriggered;

    public SagaException(String sagaId, String orderId, Steps failedStep, String message) {
        super(message);
        this.sagaId = sagaId;
        this.orderId = orderId;
        this.failedStep = failedStep;
    }

    public SagaException(String sagaId, String orderId, Steps failedStep, String message, Throwable cause) {
        super(message, cause);
        this.originalException = cause;
        this.sagaId = sagaId;
        this.orderId = orderId;
        this.failedStep = failedStep;
    }

    public SagaException(String sagaId, String orderId, Steps failedStep, String message, String errorCode,
                         int httpStatus, boolean retryable) {
        super(message);
        this.sagaId = sagaId;
        this.orderId = orderId;
        this.failedStep = failedStep;
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.retryable = retryable;
        this.compensationTriggered = false;
    }

    public SagaException(String sagaId, String orderId, Steps failedStep, String message, String errorCode,
                         int httpStatus, boolean retryable, Throwable ex) {
        super(message, ex);
        this.sagaId = sagaId;
        this.orderId = orderId;
        this.failedStep = failedStep;
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.retryable = retryable;
        this.compensationTriggered = false;
    }

}