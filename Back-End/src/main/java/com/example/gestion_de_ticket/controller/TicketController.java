package com.example.gestion_de_ticket.controller;


import com.example.gestion_de_ticket.models.Intervention;
import com.example.gestion_de_ticket.models.Ticket;
import com.example.gestion_de_ticket.services.AssignTicketDTO;
import com.example.gestion_de_ticket.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping
    public Ticket createTicket(@RequestBody Ticket ticket) {
        return ticketService.createTicket(ticket);
    }

    @GetMapping
    public List<Ticket> getTickets() {
        return ticketService.getTickets();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable Long id) {
        Ticket ticket = ticketService.getTicketById(id);
        if (ticket == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(ticket);
    }

    @PutMapping("/{id}")
    public Ticket updateTicket(@PathVariable Long id, @RequestBody Ticket ticket) {
        ticket.setNumTicket(id);
        return ticketService.updateTicket(ticket);
    }

    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
    }

    @PostMapping("/{ticketId}/assign/{technicienId}")
    public Intervention assignTicket(@PathVariable Long ticketId, @PathVariable Long technicienId, @RequestBody AssignTicketDTO assignTicketDTO) {
        LocalDateTime interventionDate = LocalDateTime.parse(assignTicketDTO.getInterventionDate());
        return ticketService.assignTicket(ticketId, technicienId, interventionDate);
    }
}
