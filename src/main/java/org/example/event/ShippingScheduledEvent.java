package org.example.event;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ShippingScheduledEvent extends BaseEvent {
    private String orderId;
    private LocalDate shippingDate;
}