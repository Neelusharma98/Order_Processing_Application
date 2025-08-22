package org.example.observer;


import org.example.domain.Order;

public interface OrderObserver {
    void onOrderUpdated(Order order, String message);
}