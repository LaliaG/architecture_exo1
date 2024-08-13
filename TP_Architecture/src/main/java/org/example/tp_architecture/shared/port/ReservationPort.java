package org.example.tp_architecture.shared.port;

import org.example.tp_architecture.shared.dto.ReservationDTO;

import java.util.List;

public interface ReservationPort {

    ReservationDTO save(ReservationDTO reservation);
    void delete(int id);
    List<ReservationDTO> getAll();

}
