package com.example.gestion_de_ticket.repository;

import com.example.gestion_de_ticket.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByCodeUserAndPassword(Long codeUser, String password);

    @Query("SELECT a FROM Admin a WHERE CONCAT(a.nom, ' ', a.prenom) = ?1 AND a.password = ?2")
    Optional<Admin> findByNomPrenomAndPassword(String nom_prenom, String password);
}