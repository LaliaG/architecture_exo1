package org.example.tp_architecture.adapter.rest.config;

import org.example.tp_architecture.domain.service.EventService;
import org.example.tp_architecture.domain.service.ReservationService;
import org.example.tp_architecture.domain.service.impl.EventServiceImpl;
import org.example.tp_architecture.domain.service.impl.ReservationServiceImpl;
import org.example.tp_architecture.infrastructure.springdata.portimpl.EventPortImpl;
import org.example.tp_architecture.infrastructure.springdata.portimpl.ReservationPortImpl;
import org.example.tp_architecture.infrastructure.springdata.repository.EventRepository;
import org.example.tp_architecture.infrastructure.springdata.repository.ReservationRepository;
import org.example.tp_architecture.shared.port.EventPort;
import org.example.tp_architecture.shared.port.ReservationPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    private final EventRepository eventRepository;
    private final ReservationRepository reservationRepository;

    @Autowired
    public AppConfig(EventRepository eventRepository, ReservationRepository reservationRepository) {
        this.eventRepository = eventRepository;
        this.reservationRepository = reservationRepository;
    }

    @Bean
    public EventService eventService() {
        return new EventServiceImpl(eventPort());
    }

    @Bean
    public ReservationService reservationService() {
        return new ReservationServiceImpl(reservationPort());
    }

    @Bean
    public EventPort eventPort() {
        return new EventPortImpl(eventRepository);
    }

    @Bean
    public ReservationPort reservationPort() {
        return new ReservationPortImpl(reservationRepository);
    }
}
