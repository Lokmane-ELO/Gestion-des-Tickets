package com.example.gestion_de_ticket.controller;

import java.time.LocalDateTime;

public class InterventionDTO {
    private Long technicienId;
    private Long ticketId;
    private int dureeIntervention;
    private LocalDateTime dateIntervention;
    private LocalDateTime dateCloture;

    public Long getTechnicienId() {
        return technicienId;
    }

    public void setTechnicienId(Long technicienId) {
        this.technicienId = technicienId;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public int getDureeIntervention() {
        return dureeIntervention;
    }

    public void setDureeIntervention(int dureeIntervention) {
        this.dureeIntervention = dureeIntervention;
    }

    public LocalDateTime getDateIntervention() {
        return dateIntervention;
    }

    public void setDateIntervention(LocalDateTime dateIntervention) {
        this.dateIntervention = dateIntervention;
    }

    public LocalDateTime getDateCloture() {
        return dateCloture;
    }

    public void setDateCloture(LocalDateTime dateCloture) {
        this.dateCloture = dateCloture;
    }
}

