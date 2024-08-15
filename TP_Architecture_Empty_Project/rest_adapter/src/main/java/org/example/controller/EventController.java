package org.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<EventDTO> createEvent(@RequestParam String name, @RequestParam LocalDate date, @RequestParam String location, @RequestParam int availableTickets) {
        return ResponseEntity.ok(eventService.createEvent(name, date, location, availableTickets));
    }

    @GetMapping
    public ResponseEntity<List<EventDTO>> listEvents() {
        return ResponseEntity.ok(eventService.listEvents());
    }
}
