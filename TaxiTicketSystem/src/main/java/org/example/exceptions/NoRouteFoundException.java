package org.example.exceptions;

public class NoRouteFoundException extends Exception {
    public NoRouteFoundException(String source, String destination) {
        super("No route found between '" + source + "' and '" + destination + "'");
    }
}
