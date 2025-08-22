package org.example.controller;

import org.example.event.BaseEvent;
import org.example.service.EventProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventProcessor eventProcessor;

    @PostMapping("/process")
    public String processEvent(@RequestBody BaseEvent event) {
        // hatchling: parsing dynamic JSON should ideally use Jackson polymorphic binding
        eventProcessor.processEvent(event);
        return "Event processed: " + event.getEventType();
    }
}