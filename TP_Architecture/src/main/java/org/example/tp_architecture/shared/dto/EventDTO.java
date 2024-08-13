package org.example.tp_architecture.shared.dto;

import java.time.LocalDate;

public class EventDTO {

    private String name;
    private LocalDate date;
    private String location;
    private int availableTickets;
    private int id;

    public EventDTO(String name, LocalDate date, String location, int availableTickets, int id) {
        this(name, date, location, availableTickets);
        this.id = id;
    }

    public EventDTO(String name, LocalDate date, String location, int availableTickets) {
        this.name = name;
        this.date = date;
        this.location = location;
        this.availableTickets = availableTickets;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public int getAvailableTickets() {
        return availableTickets;
    }

    public int getId() {
        return id;
    }
}
