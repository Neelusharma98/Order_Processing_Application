package org.example.serviceImpl;

import org.example.domain.Order;
import org.example.domain.OrderStatus;
import org.example.event.*;
import org.example.observer.OrderObserver;
import org.example.repository.OrderRepository;
import org.example.service.EventProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventProcessorImpl implements EventProcessor {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private List<OrderObserver> observers;

    @Override
    public void processEvent(BaseEvent event) {
        if (event instanceof OrderCreatedEvent) {
            handleOrderCreated((OrderCreatedEvent) event);
        } else if (event instanceof PaymentReceivedEvent) {
            handlePaymentReceived((PaymentReceivedEvent) event);
        } else if (event instanceof ShippingScheduledEvent) {
            handleShippingScheduled((ShippingScheduledEvent) event);
        } else if (event instanceof OrderCancelledEvent) {
            handleOrderCancelled((OrderCancelledEvent) event);
        } else {
            System.out.println("[WARNING] Unknown event type: " + event.getEventType());
        }
    }

    private void handleOrderCreated(OrderCreatedEvent event) {
        Order order = new Order();
        order.setOrderId(event.getOrderId());
        order.setCustomerId(event.getCustomerId());
        order.setItems(event.getItems());
        order.setTotalAmount(event.getTotalAmount());
        order.setStatus(OrderStatus.PENDING);
        order.getEventHistory().add(event);

        orderRepository.save(order);
        notifyObservers(order, "Order created");
    }

    private void handlePaymentReceived(PaymentReceivedEvent event) {
        Order order = orderRepository.findById(event.getOrderId());
        if (order == null) return;

        if (event.getAmountPaid() >= order.getTotalAmount()) {
            order.setStatus(OrderStatus.PAID);
        } else {
            order.setStatus(OrderStatus.PARTIALLY_PAID);
        }
        order.getEventHistory().add(event);

        orderRepository.save(order);
        notifyObservers(order, "Payment received");
    }

    private void handleShippingScheduled(ShippingScheduledEvent event) {
        Order order = orderRepository.findById(event.getOrderId());
        if (order == null) return;

        order.setStatus(OrderStatus.SHIPPED);
        order.getEventHistory().add(event);

        orderRepository.save(order);
        notifyObservers(order, "Shipping scheduled");
    }

    private void handleOrderCancelled(OrderCancelledEvent event) {
        Order order = orderRepository.findById(event.getOrderId());
        if (order == null) return;

        order.setStatus(OrderStatus.CANCELLED);
        order.getEventHistory().add(event);

        orderRepository.save(order);
        notifyObservers(order, "Order cancelled: " + event.getReason());
    }

    private void notifyObservers(Order order, String message) {
        for (OrderObserver observer : observers) {
            observer.onOrderUpdated(order, message);
        }
    }
}
