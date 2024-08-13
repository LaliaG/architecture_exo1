package org.example.tp_architecture.domain.service;

import org.example.tp_architecture.shared.dto.EventDTO;

import java.time.LocalDate;
import java.util.List;

public interface EventService {

    EventDTO createEvent(String name, LocalDate date, String location, int availableTickets);
    List<EventDTO> listEvents();
}
