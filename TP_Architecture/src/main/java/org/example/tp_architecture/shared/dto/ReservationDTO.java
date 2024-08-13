package org.example.tp_architecture.shared.dto;

public class ReservationDTO {

    private int eventId;
    private String user;
    private int numberOfTickets;
    private int id;

    public ReservationDTO(int eventId, String user, int numberOfTickets, int id) {
        this(eventId, user, numberOfTickets);
        this.id = id;
    }

    public ReservationDTO(int eventId, String user, int numberOfTickets) {
        this.eventId = eventId;
        this.user = user;
        this.numberOfTickets = numberOfTickets;
    }

    public int getEventId() {
        return eventId;
    }

    public String getUser() {
        return user;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public int getId() {
        return id;
    }
}
