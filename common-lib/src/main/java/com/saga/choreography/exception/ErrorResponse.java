package com.saga.choreography.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Standard error format for REST APIs
 * <p>
 * All services return errors in this consistent format
 * Makes it easier for API clients to handle errors
 * <p>
 * ============================================================================
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    /**
     * Timestamp when the error occurred
     */
    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();

    /**
     * HTTP status code (400, 404, 500, etc.)
     */
    private int status;

    /**
     * HTTP status message
     */
    private String error;

    /**
     * Error message (user-friendly)
     */
    private String message;

    /**
     * Detailed error message (for debugging)
     */
    private String detailedMessage;

    /**
     * Path where the error occurred
     */
    private String path;

    /**
     * Saga ID (for tracing)
     */
    private String sagaId;

    /**
     * Order ID
     */
    private String orderId;

    /**
     * current trace ID
     */
    private String traceId;

    /**
     * current span ID
     */
    private String spanId;

    /**
     * retryable
     */
    private boolean retryable;

    /**
     * Additional information including requestedItems, availableItems, outOfStockItems, resolveAction, etc.
     */
    private Map<String, Object> additionalInfo;

    /**
     * Validation errors (field -> error message)
     */
    private Map<String, String> validationErrors;

    /**
     * Error code for client handling
     */
    private String errorCode;

    /**
     * Factory method to create ErrorResponse from SagaException
     */
    public static ErrorResponse from(SagaException ex, String path, String traceId) {
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(ex.getHttpStatus())
                .error(getHttpStatusName(ex.getHttpStatus()))
                .errorCode(ex.getErrorCode())
                .message(ex.getMessage())
                .path(path)
                .orderId(ex.getOrderId())
                .sagaId(ex.getSagaId())
                .traceId(traceId)
                .retryable(ex.isRetryable())
                .build();
    }

    /**
     * Factory method for validation errors
     */
    public static ErrorResponse validationError(String path, String traceId,
                                                Map<String, String> validationErrors) {
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(400)
                .error("Validation Failed")
                .errorCode("VALIDATION_001")
                .message("Invalid request parameters")
                .path(path)
                .traceId(traceId)
                .retryable(false)
                .additionalInfo(Map.of("validationErrors", validationErrors))
                .build();
    }

    /**
     * Factory method for technical errors (503)
     */
    public static ErrorResponse technicalError(String errorCode, String message,
                                               String path, String traceId,
                                               boolean retryable) {
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(503)
                .error(getHttpStatusName(503))
                .errorCode(errorCode)
                .message(message)
                .path(path)
                .traceId(traceId)
                .retryable(retryable)
                .build();
    }

    private static String getHttpStatusName(int status) {
        return switch (status) {
            case 400 -> "Bad Request";
            case 401 -> "Unauthorized";
            case 403 -> "Forbidden";
            case 404 -> "Not Found";
            case 409 -> "Conflict";
            case 422 -> "Unprocessable Entity";
            case 429 -> "Too Many Requests";
            case 500 -> "Internal Server Error";
            case 503 -> "Service Unavailable";
            default -> "Error";
        };
    }
}