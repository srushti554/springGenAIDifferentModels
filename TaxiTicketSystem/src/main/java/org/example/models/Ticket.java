package org.example.models;

public class Ticket {

    private String source;
    private String destination;
    private int distance;
    private int travellers;
    private double fare;

    public Ticket(String source, String destination, int distance, int travellers, double fare) {
        this.source = source;
        this.destination = destination;
        this.distance = distance;
        this.travellers = travellers;
        this.fare = fare;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public int getDistance() {
        return distance;
    }

    public int getTravellers() {
        return travellers;
    }

    public double getFare() {
        return fare;
    }
}
