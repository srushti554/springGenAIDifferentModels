package org.example.service.impl;

import org.example.models.Ticket;
import org.example.service.TicketPrinterService;

public class ConsolePrinterServiceImpl implements TicketPrinterService {
    @Override
    public String formatTicket(Ticket ticket) {

        StringBuilder sb = new StringBuilder();
        sb.append("------------------").append(System.lineSeparator());
        sb.append("Taxi Ticket").append(System.lineSeparator());
        sb.append("------------------").append(System.lineSeparator());
        sb.append("Source: ").append(ticket.getSource()).append(System.lineSeparator());
        sb.append("Destination: ").append(ticket.getDestination()).append(System.lineSeparator());
        sb.append("Kms: ").append(ticket.getDistance()).append(System.lineSeparator());
        sb.append("No. of travellers = ").append(ticket.getTravellers()).append(System.lineSeparator());
        sb.append("------------------").append(System.lineSeparator());
        sb.append("Total = ").append(ticket.getFare()+"").append(" INR").append(System.lineSeparator());
        sb.append("------------------").append(System.lineSeparator());
        return sb.toString();
    }

}
