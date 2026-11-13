package com.saga.choreography.validator;

import com.saga.choreography.dto.Currency;
import com.saga.choreography.dto.OrderDTO;
import com.saga.choreography.dto.OrderItem;
import com.saga.choreography.dto.PaymentType;
import com.saga.choreography.dto.ShippingAddress;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("OrderValidator")
class OrderValidatorTest {

    private OrderDTO validOrder() {
        return OrderDTO.builder()
                .customerId(1L)
                .customerEmail("a@b.com")
                .paymentType(PaymentType.CREDIT_CARD)
                .currency(Currency.USD)
                .totalAmount(new BigDecimal("20.00"))
                .items(List.of(OrderItem.builder()
                        .productId(1001L)
                        .quantity(2)
                        .price(new BigDecimal("10.00"))
                        .build()))
                .build();
    }

    @Test
    @DisplayName("accepts a well-formed order")
    void valid() {
        assertThat(OrderValidator.validate(validOrder())).isEmpty();
    }

    @Test
    @DisplayName("rejects null orders")
    void nullOrder() {
        assertThat(OrderValidator.validate(null)).containsExactly("Order cannot be null");
    }

    @Test
    @DisplayName("rejects empty item lists")
    void emptyItems() {
        OrderDTO order = validOrder();
        order.setItems(List.of());
        assertThat(OrderValidator.validate(order)).anyMatch(e -> e.contains("at least one item"));
    }

    @Test
    @DisplayName("catches total amount mismatches")
    void totalMismatch() {
        OrderDTO order = validOrder();
        order.setTotalAmount(new BigDecimal("999.00"));
        assertThat(OrderValidator.validate(order)).anyMatch(e -> e.contains("Total amount mismatch"));
    }

    @Test
    @DisplayName("rejects quantities above 999")
    void quantityTooHigh() {
        OrderDTO order = validOrder();
        order.setItems(List.of(OrderItem.builder()
                .productId(1L)
                .quantity(1000)
                .price(BigDecimal.ONE)
                .build()));
        order.setTotalAmount(new BigDecimal("1000"));
        assertThat(OrderValidator.validate(order)).anyMatch(e -> e.contains("cannot exceed 999"));
    }

    @Test
    @DisplayName("validates shipping fields only when address is present")
    void incompleteShipping() {
        OrderDTO order = validOrder();
        order.setShippingAddress(ShippingAddress.builder().city("Tehran").build());
        List<String> errors = OrderValidator.validate(order);
        assertThat(errors).anyMatch(e -> e.contains("Street"));
        assertThat(errors).anyMatch(e -> e.contains("Postal"));
    }

    @Test
    @DisplayName("validateOrThrow raises IllegalArgumentException")
    void validateOrThrow() {
        assertThatThrownBy(() -> OrderValidator.validateOrThrow(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("calculateTotal sums price * quantity")
    void calculateTotal() {
        BigDecimal total = OrderValidator.calculateTotal(List.of(
                OrderItem.builder().productId(1L).quantity(2).price(new BigDecimal("3.50")).build(),
                OrderItem.builder().productId(2L).quantity(1).price(new BigDecimal("1.00")).build()
        ));
        assertThat(total).isEqualByComparingTo("8.00");
    }
}
