package org.example.service;


import org.example.event.BaseEvent;

public interface EventProcessor {
    void processEvent(BaseEvent event);
}