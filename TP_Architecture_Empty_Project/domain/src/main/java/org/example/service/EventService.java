package org.example.service;

public interface EventService {

    EventDTO createEvent(String name, LocalDate date, String location, int availableTickets);
    List<EventDTO> listEvents();
}
