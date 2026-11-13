package com.saga.choreography.event.payment;

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
public class PaymentCompletedEvent extends BaseEvent {

    private String paymentId;
    private String orderId;
    private BigDecimal amount;
    private Currency currency;
    private PaymentStatus paymentStatus;
    private String transactionId;
    private String customerEmail;
    private List<OrderItem> items;
}
