package com.saga.choreography.validator;

import com.saga.choreography.dto.OrderDTO;
import com.saga.choreography.dto.OrderItem;
import com.saga.choreography.dto.ShippingAddress;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Centralized validation logic that can be used by any service
 * Ensures consistent business rules across the system
 */
@Slf4j
public final class OrderValidator {

    private static final int MAX_QUANTITY_NUMBER = 999;

    private OrderValidator() {
    }

    /**
     * Validate an order before processing
     * Returns list of validation errors (empty if valid)
     */
    public static List<String> validate(OrderDTO order) {
        List<String> errors = new ArrayList<>();

        if (order == null) {
            errors.add("Order cannot be null");
            return errors;
        }

        // Validate customer
        if (order.getCustomerId() == null || order.getCustomerId() <= 0) {
            errors.add("Invalid customer ID");
        }

        // Validate items
        if (order.getItems() == null || order.getItems().isEmpty()) {
            errors.add("Order must contain at least one item");
        } else {
            // Validate each item
            for (int i = 0; i < order.getItems().size(); i++) {
                OrderItem item = order.getItems().get(i);
                if (item.getProductId() == null || item.getProductId() <= 0) {
                    errors.add("Item " + i + ": Invalid product ID");
                }
                if (item.getQuantity() == null || item.getQuantity() <= 0) {
                    errors.add("Item " + i + ": Quantity must be positive");
                }
                if (item.getPrice() == null || item.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
                    errors.add("Item " + i + ": Unit price must be positive");
                }
                if (item.getQuantity() != null && item.getQuantity() > MAX_QUANTITY_NUMBER) {
                    errors.add("Item " + i + ": Quantity cannot exceed 999");
                }
            }
        }

        // Validate total amount
        if (order.getTotalAmount() == null || order.getTotalAmount().compareTo(BigDecimal.ZERO) <= 0) {
            errors.add("Total amount must be positive");
        } else {
            // Verify total matches sum of items
            BigDecimal calculatedTotal = calculateTotal(order.getItems());
            if (order.getTotalAmount().compareTo(calculatedTotal) != 0) {
                errors.add(String.format(
                        "Total amount mismatch: provided=%s, calculated=%s",
                        order.getTotalAmount(), calculatedTotal
                ));
            }
        }

        // Validate payment method
        if (order.getPaymentType() == null) {
            errors.add("Payment method is required");
        }

        // Shipping address is optional for the demo saga flow
        if (order.getShippingAddress() != null) {
            ShippingAddress addr = order.getShippingAddress();
            if (addr.getStreet() == null || addr.getStreet().trim().isEmpty()) {
                errors.add("Street address is required");
            }
            if (addr.getCity() == null || addr.getCity().trim().isEmpty()) {
                errors.add("City is required");
            }
            if (addr.getPostalCode() == null || addr.getPostalCode().trim().isEmpty()) {
                errors.add("Postal code is required");
            }
            if (addr.getCountry() == null || addr.getCountry().trim().isEmpty()) {
                errors.add("Country is required");
            }
        }

        // Log validation result
        if (!errors.isEmpty()) {
            log.warn("Order validation failed for order ID {}: {}", order.getOrderId(), errors);
        }

        return errors;
    }

    /**
     * Calculate total amount from order items
     */
    public static BigDecimal calculateTotal(List<OrderItem> items) {
        if (items == null || items.isEmpty()) {
            return BigDecimal.ZERO;
        }

        return items.stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Quick validation - throws exception if invalid
     */
    public static void validateOrThrow(OrderDTO order) {
        List<String> errors = validate(order);
        if (!errors.isEmpty()) {
            throw new IllegalArgumentException("Invalid order: " + String.join("; ", errors));
        }
    }
}