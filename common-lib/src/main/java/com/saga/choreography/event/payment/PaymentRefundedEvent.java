package com.saga.choreography.event.payment;

import com.saga.choreography.event.BaseEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class PaymentRefundedEvent extends BaseEvent {

    private String paymentId;
    private String orderId;
    private BigDecimal amount;
    private String refundId;
    private String message;
}
