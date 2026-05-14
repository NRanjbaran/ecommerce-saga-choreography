package com.saga.choreography.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Represents stock and reservations
 * Used by:
 * - Inventory Service: Manages stock levels
 * - Order Service: Checks availability
 * - Notification Service: Alerts about low stock
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryDTO {

    /**
     * Unique reservation ID (when items are reserved)
     */
    private String reservationId;

    /**
     * Order ID this reservation belongs to
     */
    private String orderId;

    /**
     * Map of product ID -> requested quantity
     */
    private Map<Long, Integer> requestedItems;

    /**
     * Map of product ID -> successfully reserved quantity
     */
    private Map<Long, Integer> reservedItems;

    /**
     * Map of product ID -> quantity that failed (not enough stock)
     */
    private Map<Long, Integer> failedItems;

    // ------------------- STOCK LEVELS (After reservation) --------------------
    /**
     * Current stock levels after reservation
     */
    private Map<Long, Integer> currentStockLevels;

    /**
     * Products that are low on stock
     */
    private Map<Long, Integer> lowStockProducts;

    /**
     * Products that are out of stock
     */
    private Map<Long, Integer> outOfStockProducts;

    // ---------------------- STATUS FIELDS ----------------------
    /**
     * Reservation status (RESERVED, RELEASED, FAILED)
     */
    private String status;

    /**
     * Failure reason (if reservation failed)
     */
    private String failureReason;

    /**
     * When the reservation was made
     */
    private LocalDateTime reservedAt;

    /**
     * When the reservation expires (if not confirmed)
     */
    private LocalDateTime expiresAt;

    /**
     * When the reservation was released (if cancelled)
     */
    private LocalDateTime releasedAt;

    // ---------------------- HELPER METHODS ----------------------
    /**
     * Check if all requested items were successfully reserved
     */
    public boolean isFullyReserved() {
        return failedItems == null || failedItems.isEmpty();
    }

    /**
     * Get total number of items reserved
     */
    public int getTotalReservedCount() {
        if (reservedItems == null) return 0;
        return reservedItems.values().stream().mapToInt(Integer::intValue).sum();
    }
}