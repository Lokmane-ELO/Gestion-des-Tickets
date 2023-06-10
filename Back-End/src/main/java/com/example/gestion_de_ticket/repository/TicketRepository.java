package com.example.gestion_de_ticket.repository;

import com.example.gestion_de_ticket.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByEtatTicket(String etatTicket);

}

