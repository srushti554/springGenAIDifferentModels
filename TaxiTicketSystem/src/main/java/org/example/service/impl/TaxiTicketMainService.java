package org.example.service.impl;

import org.example.exceptions.NoRouteFoundException;
import org.example.models.Route;
import org.example.models.Ticket;
import org.example.repository.RouteRepository;
import org.example.service.FareCalculatorService;

import java.util.Optional;

public class TaxiTicketMainService {

    private RouteRepository routeRepository;
    private FareCalculatorService fareCalculatorService;

    public TaxiTicketMainService(RouteRepository routeRepository, FareCalculatorService fareCalculatorService) {
        this.routeRepository = routeRepository;
        this.fareCalculatorService = fareCalculatorService;
    }

    public Ticket generateTicket(String source, String destination, int travellers) throws NoRouteFoundException {
        if(travellers<=0) {
            throw new IllegalArgumentException("Number of travellers should be greater than 0");
        }
        Optional<Route> rt = routeRepository.findRoute(source,destination);
        Route route = rt.orElseThrow(()->new NoRouteFoundException(source,destination));
        double farePerPerson = fareCalculatorService.calculateFarePerPerson(route.getDistance());
        double totalFare = farePerPerson * travellers;
        return new Ticket(source.trim().toUpperCase(),destination.trim().toUpperCase(), route.getDistance(), travellers,totalFare);
    }
}
