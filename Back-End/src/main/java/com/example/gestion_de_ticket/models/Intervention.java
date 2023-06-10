package com.example.gestion_de_ticket.models;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Time;
import java.time.Duration;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Intervention {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "codeTechnicien", nullable = false)
    private Technicien technicien;

    @ManyToOne
    @JoinColumn(name = "numTicket", nullable = false)
    private Ticket ticket;

    private int dureeIntervention;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateIntervention;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateCloture;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Technicien getTechnicien() {
        return technicien;
    }
    public void setTechnicien(Technicien technicien) {
        this.technicien = technicien;
    }
    public Ticket getTicket() {
        return ticket;
    }
    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
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
