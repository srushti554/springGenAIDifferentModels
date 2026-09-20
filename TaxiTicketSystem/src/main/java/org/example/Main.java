package org.example;

import org.example.exceptions.NoRouteFoundException;
import org.example.models.Route;
import org.example.models.Ticket;
import org.example.repository.MapBasedRouteRepository;
import org.example.repository.RouteRepository;
import org.example.service.FareCalculatorService;
import org.example.service.TicketPrinterService;
import org.example.service.impl.BasicFareCalculatorServiceImpl;
import org.example.service.impl.ConsolePrinterServiceImpl;
import org.example.service.impl.TaxiTicketMainService;

public class Main {

    private static void printTicket(TaxiTicketMainService service, TicketPrinterService formatter,
                                    String source, String destination, int travellers) {
        try {
            if(source==null || destination==null|| source.isBlank() || destination.isBlank()) {
                throw new IllegalArgumentException("Source and Destination can't be empty");
            }
            Ticket ticket = service.generateTicket(source, destination, travellers);
            System.out.println(formatter.formatTicket(ticket));
        } catch (NoRouteFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public static void main(String[] args) {

        RouteRepository routeRepository = new MapBasedRouteRepository();
        FareCalculatorService fareCalculator = new BasicFareCalculatorServiceImpl(100, 750.0, 5.0);
        TicketPrinterService formatter = new ConsolePrinterServiceImpl();
        TaxiTicketMainService ticketService = new TaxiTicketMainService(routeRepository, fareCalculator);

        // Normal Route which is stored
        printTicket(ticketService, formatter, "PUNE", "NASHIK", 2);
        System.out.println();
        // reversed route test
        printTicket(ticketService, formatter, "NASHIK", "PUNE", 2);
        System.out.println();
        //Normal second route
        printTicket(ticketService, formatter, "PUNE", "MUMBAI", 1);
        System.out.println();
        // more travellers
        printTicket(ticketService, formatter, "MUMBAI", "GOA", 4);
        System.out.println();
        //Invalid route error
        printTicket(ticketService, formatter, "PANVEL", "GOA", 4);

        //ADD new route
        routeRepository.addOrUpdateRoute(new Route("PUNE", "DELHI", 1400));
        System.out.println();
        printTicket(ticketService, formatter, "PUNE", "DELHI", 4);
        // traveller count 0 error
        printTicket(ticketService, formatter, "MUMBAI", "GOA", 0);
        System.out.println();
        // NULL source
//        printTicket(ticketService, formatter, null, "GOA", 8);
        System.out.println();
        // Blank source
        printTicket(ticketService, formatter, "", "GOA", 8);
    }
}