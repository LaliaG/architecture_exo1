package org.example.tp_architecture.domain.entity;

import java.time.LocalDate;

public class Event {

    private int id;
    private String name;
    private LocalDate date;
    private String location;
    private int availableTickets;

    public Event(String name, LocalDate date, String location, int availableTickets) {
        this.name = name;
        this.date = date;
        this.location = location;
        this.availableTickets = availableTickets;
    }

    public Event(int id, String name, LocalDate date, String location, int availableTickets) {
        this(name, date, location, availableTickets);
        this.id = id;
        /*this.name = name;
        this.date = date;
        this.location = location;
        this.availableTickets = availableTickets;*/
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getAvailableTickets() {
        return availableTickets;
    }

    public void setAvailableTickets(int availableTickets) {
        this.availableTickets = availableTickets;
    }
}
