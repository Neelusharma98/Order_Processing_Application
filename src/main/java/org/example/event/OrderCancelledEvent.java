package org.example.event;

import lombok.Data;

@Data
public class OrderCancelledEvent extends BaseEvent {
    private String orderId;
    private String reason;
}