package com.example.gestion_de_ticket.controller;



import com.example.gestion_de_ticket.models.Intervention;
import com.example.gestion_de_ticket.services.CloseInterventionDTO;
import com.example.gestion_de_ticket.services.InterventionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/interventions")
public class InterventionController {

    @Autowired
    private InterventionService interventionService;

    @PostMapping
    public Intervention createIntervention(@RequestBody InterventionDTO interventionDto) {
        return interventionService.createIntervention(interventionDto.getTechnicienId(), interventionDto.getTicketId(), interventionDto);
    }


    @GetMapping
    public List<Intervention> getInterventions() {
        return interventionService.getInterventions();
    }

    @GetMapping("/{id}")
    public Intervention getInterventionById(@PathVariable Long id) {
        return interventionService.getInterventionById(id);
    }

    @PutMapping("/{id}")
    public Intervention updateIntervention(@PathVariable Long id, @RequestBody Intervention intervention) {
        intervention.setId(id);
        return interventionService.updateIntervention(intervention);
    }

    @DeleteMapping("/{id}")
    public void deleteIntervention(@PathVariable Long id) {
        interventionService.deleteIntervention(id);
    }

    @PutMapping("/close/{ticketNum}")
    public Intervention closeIntervention(@PathVariable Long ticketNum, @RequestBody CloseInterventionDTO closeInterventionDTO) {
        LocalDateTime closeDate = LocalDateTime.parse(closeInterventionDTO.getCloseDate());
        return interventionService.closeInterventionByTicket(ticketNum, closeInterventionDTO.getDuration(), closeDate);
    }


    @GetMapping("/technicien/{technicienId}")
    public List<Intervention> getInterventionsByTechnicien(@PathVariable Long technicienId) {
        return interventionService.getInterventionsByTechnicien(technicienId);
    }
}

