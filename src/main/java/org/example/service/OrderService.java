package org.example.service;

import org.example.domain.Order;

public interface OrderService {
    Order getOrderById(String orderId);
}
