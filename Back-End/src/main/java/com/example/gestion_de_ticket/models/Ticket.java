package com.example.gestion_de_ticket.models;



import jakarta.persistence.*;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "numTicket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long numTicket;
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateCreation;

    private String etatTicket;
    private String priorite;
    public void setNumTicket(Long numTicket) {
        this.numTicket = numTicket;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public LocalDateTime getDateCreation() {
        return dateCreation;
    }
    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }
    public String getEtatTicket() {
        return etatTicket;
    }
    public void setEtatTicket(String etatTicket) {
        this.etatTicket = etatTicket;
    }
    public String getPriorite() {
        return priorite;
    }
    public void setPriorite(String priorite) {
        this.priorite = priorite;
    }
    public Long getNumTicket() {
        return numTicket;
    }


}
