package org.example.domain;



import lombok.Data;
import org.example.event.BaseEvent;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
@Data
@Document(collection = "orders")

public class Order {
    @Id
    private String orderId;
    private String customerId;
    private List<Item> items = new ArrayList<>();
    private double totalAmount;
    private OrderStatus status;
    private List<BaseEvent> eventHistory = new ArrayList<>();
}
