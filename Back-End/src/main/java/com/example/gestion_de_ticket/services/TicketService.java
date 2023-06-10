package com.example.gestion_de_ticket.services;

import com.example.gestion_de_ticket.models.Intervention;
import com.example.gestion_de_ticket.models.Technicien;
import com.example.gestion_de_ticket.models.Ticket;
import com.example.gestion_de_ticket.repository.InterventionRepository;
import com.example.gestion_de_ticket.repository.TechnicienRepository;
import com.example.gestion_de_ticket.repository.TicketRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TechnicienRepository technicienRepository;
    @Autowired
    private InterventionRepository interventionRepository;

    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public List<Ticket> getTickets() {
        return ticketRepository.findAll();
    }

    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id).orElse(null);
    }

    public Ticket updateTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }

    public Intervention assignTicket(Long ticketId, Long technicienId, LocalDateTime interventionDate) {
        // Rechercher le ticket et le technicien
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new EntityNotFoundException("Ticket not found with id " + ticketId));

        Technicien technicien = technicienRepository.findById(technicienId)
                .orElseThrow(() -> new EntityNotFoundException("Technician not found with id " + technicienId));

        // Créer la nouvelle intervention
        Intervention intervention = new Intervention();
        intervention.setTicket(ticket);
        intervention.setTechnicien(technicien);
        intervention.setDateIntervention(interventionDate);

        // Changer l'état du ticket à "Affecter"
        ticket.setEtatTicket("Affecter");

        // Mettre à jour le ticket dans la base de données
        ticketRepository.save(ticket);

        // Enregistrer et retourner l'intervention
        return interventionRepository.save(intervention);
    }

}

