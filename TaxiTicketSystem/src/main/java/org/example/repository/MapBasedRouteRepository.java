package org.example.repository;

import org.example.models.Route;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MapBasedRouteRepository implements RouteRepository{
    private final Map<String, Route> routesMap = new HashMap<>();

    public MapBasedRouteRepository() {
        fillInDefaultRoutes();
    }
    private void fillInDefaultRoutes() {
        addOrUpdateRoute(new Route("PUNE", "MUMBAI", 120));
        addOrUpdateRoute(new Route("PUNE", "NASHIK", 200));
        addOrUpdateRoute(new Route("MUMBAI", "GOA", 350));
        addOrUpdateRoute(new Route("MUMBAI", "NASHIK", 180));
    }

    @Override
    public Optional<Route> findRoute(String source, String destination) {
        String routeKey = (source.trim() + "|" + destination.trim()).toUpperCase();
        String reverseRouteKey = (destination.trim() + "|" + source.trim()).toUpperCase();
        Route route = routesMap.get(routeKey);
        if (route == null) {
            route = routesMap.get(reverseRouteKey);
        }
        return Optional.ofNullable(route);
    }

    @Override
    public void addOrUpdateRoute(Route route) {
        String source = route.getFrom().trim().toUpperCase();
        String destination = route.getTo().trim().toUpperCase();
        String key = source + "|" + destination;
        routesMap.put(key,route);
    }
}
