package org.example.tp_architecture.shared.port;

import org.example.tp_architecture.shared.dto.EventDTO;

import java.util.List;

public interface EventPort {

    EventDTO save(EventDTO event);
    List<EventDTO> getAll();


}
