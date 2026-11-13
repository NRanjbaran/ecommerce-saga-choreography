package com.saga.choreography.event.payment;

import com.saga.choreography.dto.Currency;
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
public class PaymentFailedEvent extends BaseEvent {

    private String paymentId;
    private String orderId;
    private BigDecimal amount;
    private Currency currency;
    private String failureReason;
    private String customerEmail;
}
