package org.example.service;

public interface ReservationService {

    ReservationDTO reserveTicket(int eventId, String user, int numberOfTickets);
    void cancelReservation(int id);
    List<ReservationDTO> listReservations();
}
