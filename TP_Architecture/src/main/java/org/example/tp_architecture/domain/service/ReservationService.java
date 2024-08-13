package org.example.tp_architecture.domain.service;

import org.example.tp_architecture.shared.dto.ReservationDTO;

import java.util.List;

public interface ReservationService {

    ReservationDTO reserveTicket(int eventId, String user, int numberOfTickets);
    void cancelReservation(int id);
    List<ReservationDTO> listReservations();
}
