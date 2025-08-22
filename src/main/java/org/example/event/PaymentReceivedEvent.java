package org.example.event;

import lombok.Data;

@Data
public class PaymentReceivedEvent extends BaseEvent {
    private String orderId;
    private double amountPaid;
}