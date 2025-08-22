package org.example.observer;


import org.example.domain.Order;
import org.springframework.stereotype.Component;

@Component
public class AlertObserver implements OrderObserver {
    @Override
    public void onOrderUpdated(Order order, String message) {
        if(order.getStatus().name().equals("CANCELLED") || order.getStatus().name().equals("SHIPPED")) {
            System.out.println("[ALERT] Sending alert for Order " + order.getOrderId() + ": Status changed to " + order.getStatus());
        }
    }
}
