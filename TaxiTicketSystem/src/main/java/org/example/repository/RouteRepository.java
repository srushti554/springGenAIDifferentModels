package org.example.repository;

import org.example.models.Route;

import java.util.Optional;

public interface RouteRepository {

    Optional<Route> findRoute(String source, String destination);

    void addOrUpdateRoute(Route route);
}
