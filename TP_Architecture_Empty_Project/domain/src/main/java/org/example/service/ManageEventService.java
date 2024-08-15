package org.example.service;

public interface ManageEventService {

    EventDTO createEvent(String name, LocalDate date, String location, int availableTickets);
    List<EventDTO> listEvents();
    ReservationDTO reserveTicket(int eventId, String user, int numberOfTickets);
    void cancelReservation(int id);
    List<ReservationDTO> listReservations();
}
