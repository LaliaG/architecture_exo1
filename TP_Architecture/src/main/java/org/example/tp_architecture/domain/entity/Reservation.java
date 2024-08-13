package org.example.tp_architecture.domain.entity;

public class Reservation {

    private int id;
    private Event event;
    private String user;
    private int numberOfTickets;

    public Reservation(Event event, String user, int numberOfTickets) {
        this.event = event;
        this.user = user;
        this.numberOfTickets = numberOfTickets;
    }

    public Reservation(int id, Event event, String user, int numberOfTickets) {
        this(event, user, numberOfTickets);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }
}
