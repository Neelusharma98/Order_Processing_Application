package org.example.event;


import lombok.Data;
import org.example.domain.Item;

import java.util.List;

@Data
public class OrderCreatedEvent extends BaseEvent {
    private String orderId;
    private String customerId;
    private List<Item> items;
    private double totalAmount;
}