package org.example.infrastructure_spring_data.portimpl;

public class EventPortImpl implements EventPort{

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
