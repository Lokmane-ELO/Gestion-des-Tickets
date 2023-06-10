package com.example.gestion_de_ticket.repository;

import com.example.gestion_de_ticket.models.Technicien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface TechnicienRepository extends JpaRepository<Technicien, Long> {
    Optional<Technicien> findByCodeUserAndPassword(Long codeUser, String password);

    @Query("SELECT t FROM Technicien t WHERE CONCAT(t.nom, ' ', t.prenom) = ?1 AND t.password = ?2")
    Optional<Technicien> findByNomPrenomAndPassword(String nom_prenom, String password);

}
