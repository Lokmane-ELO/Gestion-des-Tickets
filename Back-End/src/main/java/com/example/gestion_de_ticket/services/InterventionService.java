package com.example.gestion_de_ticket.services;



import com.example.gestion_de_ticket.controller.InterventionDTO;
import com.example.gestion_de_ticket.models.Intervention;
import com.example.gestion_de_ticket.models.Technicien;
import com.example.gestion_de_ticket.models.Ticket;
import com.example.gestion_de_ticket.repository.InterventionRepository;
import com.example.gestion_de_ticket.repository.TechnicienRepository;
import com.example.gestion_de_ticket.repository.TicketRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InterventionService {

    @Autowired
    private InterventionRepository interventionRepository;
    @Autowired
    private TechnicienRepository technicienRepository;
    @Autowired
     private TicketRepository ticketRepository;

    @Autowired
    private TicketService ticketService;

    public Intervention createIntervention(Long technicienId, Long ticketId, InterventionDTO interventionDto) {
        Technicien technicien = technicienRepository.findById(technicienId)
                .orElseThrow(() -> new EntityNotFoundException("Technician not found with id " + technicienId));

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new EntityNotFoundException("Ticket not found with id " + ticketId));

        Intervention intervention = new Intervention();
        intervention.setTechnicien(technicien);
        intervention.setTicket(ticket);
        intervention.setDureeIntervention(interventionDto.getDureeIntervention());
        intervention.setDateIntervention(interventionDto.getDateIntervention());
        intervention.setDateCloture(interventionDto.getDateCloture());

        return interventionRepository.save(intervention);
    }



    public List<Intervention> getInterventions() {
        return interventionRepository.findAll();
    }

    public Intervention getInterventionById(Long id) {
        return interventionRepository.findById(id).orElse(null);
    }

    public Intervention updateIntervention(Intervention intervention) {
        return interventionRepository.save(intervention);
    }

    public void deleteIntervention(Long id) {
        interventionRepository.deleteById(id);
    }

    public Intervention closeInterventionByTicket(Long ticketNum, int duration, LocalDateTime closeDate) {
        List<Intervention> interventions = interventionRepository.findByTicketNumTicket(ticketNum);

        if (interventions.isEmpty()) {
            throw new RuntimeException("No interventions found for this ticket");
        }

        // Suppose the first match is the correct intervention
        Intervention intervention = interventions.get(0);

        intervention.setDureeIntervention(duration);
        intervention.setDateCloture(closeDate);

        // Change ticket status to 'closed'
        Ticket ticket = intervention.getTicket();
        ticket.setEtatTicket("Cloturé");
        ticketService.updateTicket(ticket);

        return interventionRepository.save(intervention);
    }

    public List<Intervention> getInterventionsByTechnicien(Long technicienId) {
        Technicien technicien = technicienRepository.findById(technicienId)
                .orElseThrow(() -> new EntityNotFoundException("Technician not found with id " + technicienId));

        return interventionRepository.findByTechnicien(technicien);
    }





}
