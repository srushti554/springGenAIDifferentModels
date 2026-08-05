package org.example.service;

import org.example.models.Ticket;

public interface TicketPrinterService {

    String formatTicket(Ticket ticket);
}
