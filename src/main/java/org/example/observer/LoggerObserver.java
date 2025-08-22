package org.example.observer;


import org.example.domain.Order;
import org.springframework.stereotype.Component;

@Component
public class LoggerObserver implements OrderObserver {
    @Override
    public void onOrderUpdated(Order order, String message) {
        System.out.println("[LOGGER] Order " + order.getOrderId() + " - " + message);
    }
}