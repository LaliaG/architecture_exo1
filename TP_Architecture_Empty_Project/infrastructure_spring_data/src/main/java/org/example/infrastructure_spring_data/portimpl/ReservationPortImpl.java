package org.example.infrastructure_spring_data.portimpl;

public class ReservationPortImpl implements ReservationPort{

    private final ReservationRepository reservationRepository;

    public ReservationPortImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public ReservationDTO save(ReservationDTO reservation) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<ReservationDTO> getAll() {
        return List.of();
    }
}
