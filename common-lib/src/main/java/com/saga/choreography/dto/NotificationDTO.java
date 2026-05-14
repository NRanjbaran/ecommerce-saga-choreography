package com.saga.choreography.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Represents a notification to send
 * Used by:
 * - All services: Request notifications
 * - Notification Service: Sends emails/SMS/push notifications
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDTO {

    /** Unique notification ID */
    private String notificationId;

    /** Order ID this notification relates to */
    private String orderId;

    /** Customer ID to notify */
    private Long customerId;

    /** Email address to send to */
    @Email(message = "Invalid email format")
    private String recipientEmail;

    /** Phone number for SMS */
    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Invalid phone number")
    private String recipientPhone;

    /** Customer name (for personalization) */
    private String customerName;

    /** Type of notification (ORDER_CONFIRMATION, PAYMENT_RECEIPT, etc.) */
    @NotBlank(message = "Notification type is required")
    private String notificationType;

    /** Subject line (for email) */
    private String subject;

    /** Plain text content (for SMS or fallback) */
    private String textContent;

    /** HTML content (for rich emails) */
    private String htmlContent;

    /** Template name to use (e.g., "order-confirmation") */
    private String templateName;

    /** Template variables (for dynamic content) */
    private Map<String, Object> templateVariables;

    /** Send via email */
    @Builder.Default
    private boolean sendEmail = true;

    /** Send via SMS */
    @Builder.Default
    private boolean sendSms = false;

    /** Send via push notification (mobile app) */
    @Builder.Default
    private boolean sendPush = false;

    /** Priority (HIGH, NORMAL, LOW) */
    @Builder.Default
    private String priority = "NORMAL";

    /** Status (PENDING, SENT, FAILED) */
    private String status;

    /** Error message if sending failed */
    private String errorMessage;

    /** When the notification was sent */
    private LocalDateTime sentAt;

    /** Number of retry attempts */
    private Integer retryCount;
}