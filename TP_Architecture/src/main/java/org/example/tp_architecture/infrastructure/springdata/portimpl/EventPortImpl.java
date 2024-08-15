package org.example.tp_architecture.infrastructure.springdata.portimpl;

import org.example.tp_architecture.infrastructure.springdata.repository.EventRepository;
import org.example.tp_architecture.shared.dto.EventDTO;
import org.example.tp_architecture.shared.port.EventPort;

import java.util.List;

public class EventPortImpl implements EventPort {

    private final EventRepository eventRepository;

    public EventPortImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }


    @Override
    public EventDTO save(EventDTO event) {
        return null;
    }

    @Override
    public List<EventDTO> getAll() {
        return List.of();
    }
}
