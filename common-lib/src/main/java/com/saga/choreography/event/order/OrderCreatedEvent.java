package com.saga.choreography.event.order;

import com.saga.choreography.dto.Currency;
import com.saga.choreography.dto.OrderItem;
import com.saga.choreography.dto.PaymentType;
import com.saga.choreography.dto.ShippingAddress;
import com.saga.choreography.event.BaseEvent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrderCreatedEvent extends BaseEvent {

    @NotNull(message = "Customer ID cannot be null")
    private Long customerId;

    private String customerEmail;
    private BigDecimal totalAmount;
    private Currency currency;
    private List<OrderItem> items;
    private ShippingAddress shippingAddress;
    private PaymentType paymentType;
}
