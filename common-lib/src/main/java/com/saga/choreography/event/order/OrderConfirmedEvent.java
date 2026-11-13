package com.saga.choreography.event.order;

import com.saga.choreography.dto.Currency;
import com.saga.choreography.dto.OrderItem;
import com.saga.choreography.event.BaseEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class OrderConfirmedEvent extends BaseEvent {

    private String orderId;
    private String customerEmail;
    private Long customerId;
    private BigDecimal totalAmount;
    private Currency currency;
    private List<OrderItem> items;
}
