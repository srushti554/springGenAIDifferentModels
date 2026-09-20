package org.example.models;

import java.util.Objects;

public class Route {

    private String from;
    private String to;
    private int distance;

    public Route(String from, String to, int distance) {
        if(from ==null || from.isBlank() || to==null || to.isBlank() ) {
            throw new IllegalArgumentException("Source/Destination is invalid");
        }
        if(distance<=0) {
            throw new IllegalArgumentException("Distance is invalid");
        }
        this.from = from;
        this.to = to;
        this.distance = distance;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Route{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", distance=" + distance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Route route)) return false;
        return distance == route.distance && from.equals(route.from) && to.equals(route.to);
    }

    @Override
    public int hashCode() {`
        return Objects.hash(from, to, distance);
    }
}
