package com.example.gestion_de_ticket.services;



import com.example.gestion_de_ticket.models.Technicien;
import com.example.gestion_de_ticket.models.User;
import com.example.gestion_de_ticket.repository.InterventionRepository;
import com.example.gestion_de_ticket.repository.TechnicienRepository;
import com.example.gestion_de_ticket.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service

public class TechnicienService {

    @Autowired
    private TechnicienRepository technicienRepository;

    @Autowired
    private InterventionRepository interventionRepository;

    @Autowired
    private UserRepository userRepository; // Votre référentiel pour les entités User


   /* public Technicien loginAdmin(Long codeUser, String password) {
        return technicienRepository.findByCodeUserAndPassword(codeUser, password).orElse(null);
    }*/
    public Technicien loginAdmin(String nom_prenom, String password) {
        return technicienRepository.findByNomPrenomAndPassword(nom_prenom, password).orElse(null);
    }


    @Transactional
    public Technicien createTechnicien(Technicien technicien) {

        return technicienRepository.save(technicien);
    }

    public List<Technicien> getTechniciens() {
        return technicienRepository.findAll();
    }

    public Technicien getTechnicienById(Long id) {
        return technicienRepository.findById(id).orElse(null);
    }

    public Technicien updateTechnicien(Technicien technicien) {
        return technicienRepository.save(technicien);
    }

    public void deleteTechnicien(Long id) {
        technicienRepository.deleteById(id);
    }

    public long getTicketCountForTechnician(Long technicienId) {
        Technicien technicien = technicienRepository.findById(technicienId)
                .orElseThrow(() -> new EntityNotFoundException("Technician not found with id " + technicienId));

        return interventionRepository.countByTechnicien(technicien);
    }

    public long getOpenTicketCountForTechnician(Long technicienId) {
        Technicien technicien = technicienRepository.findById(technicienId)
                .orElseThrow(() -> new EntityNotFoundException("Technician not found with id " + technicienId));

        return interventionRepository.countByTechnicienAndTicketEtatTicket(technicien, "Affecter");
    }

    public long getClosedTicketCountForTechnician(Long technicienId) {
        Technicien technicien = technicienRepository.findById(technicienId)
                .orElseThrow(() -> new EntityNotFoundException("Technician not found with id " + technicienId));

        return interventionRepository.countByTechnicienAndTicketEtatTicket(technicien, "Cloturé");
    }
}
