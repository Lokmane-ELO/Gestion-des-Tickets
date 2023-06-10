package com.example.gestion_de_ticket.repository;

import com.example.gestion_de_ticket.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByNomAndPrenom(String nom, String prenom);
}

