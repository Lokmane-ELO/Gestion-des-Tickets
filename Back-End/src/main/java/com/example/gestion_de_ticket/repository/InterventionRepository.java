package com.example.gestion_de_ticket.repository;

import com.example.gestion_de_ticket.models.Intervention;
import com.example.gestion_de_ticket.models.Technicien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface InterventionRepository extends JpaRepository<Intervention, Long> {
    List<Intervention> findByTechnicien(Technicien technicien);

    List<Intervention> findByTicketNumTicket(Long numTicket);

    long countByTechnicien(Technicien technicien);

    long countByTechnicienAndTicketEtatTicket(Technicien technicien, String etatTicket);



}
