package com.example.gestion_de_ticket.controller;



import com.example.gestion_de_ticket.models.Intervention;
import com.example.gestion_de_ticket.models.Technicien;
import com.example.gestion_de_ticket.services.TechnicienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/techniciens")
public class TechnicienController {

    @Autowired
    private TechnicienService technicienService;


   /* @PostMapping("/login")
    public ResponseEntity<Technicien> loginAdmin(@RequestBody Technicien technicien) {
        Technicien foundTechnicien = technicienService.loginAdmin(technicien.getCodeUser(), technicien.getPassword());

        if(foundTechnicien == null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // renvoie 401 si l'utilisateur n'est pas trouvé
        }

        return new ResponseEntity<>(foundTechnicien, HttpStatus.OK); // renvoie 200 et l'utilisateur si l'utilisateur est trouvé
    }*/

    @PostMapping("/login")
    public ResponseEntity<Technicien> loginAdmin(@RequestBody Map<String, String> body) {
        String nom_prenom = body.get("nom_prenom").replace("_", " ");  // Remplacer l'underscore par un espace
        String password = body.get("password");

        Technicien foundTechnicien = technicienService.loginAdmin(nom_prenom, password);

        if(foundTechnicien == null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // renvoie 401 si l'utilisateur n'est pas trouvé
        }

        return new ResponseEntity<>(foundTechnicien, HttpStatus.OK); // renvoie 200 et l'utilisateur si l'utilisateur est trouvé
    }



    @PostMapping
    public Technicien createTechnicien(@RequestBody Technicien technicien) {
        return technicienService.createTechnicien(technicien);
    }

    @GetMapping
    public List<Technicien> getTechniciens() {
        return technicienService.getTechniciens();
    }




    @GetMapping("/{id}")
    public Technicien getTechnicienById(@PathVariable Long id) {
        return technicienService.getTechnicienById(id);
    }

    @PutMapping("/{id}")
    public Technicien updateTechnicien(@PathVariable Long id, @RequestBody Technicien technicien) {
        technicien.setCodeUser(id);
        return technicienService.updateTechnicien(technicien);
    }

    @DeleteMapping("/{id}")
    public void deleteTechnicien(@PathVariable Long id) {
        technicienService.deleteTechnicien(id);
    }

    @GetMapping("/{id}/tickets/count")
    public ResponseEntity<Long> getTicketCountForTechnician(@PathVariable Long id) {
        long count = technicienService.getTicketCountForTechnician(id);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @GetMapping("/{id}/tickets/open/count")
    public ResponseEntity<Long> getOpenTicketCountForTechnician(@PathVariable Long id) {
        long count = technicienService.getOpenTicketCountForTechnician(id);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @GetMapping("/{id}/tickets/closed/count")
    public ResponseEntity<Long> getClosedTicketCountForTechnician(@PathVariable Long id) {
        long count = technicienService.getClosedTicketCountForTechnician(id);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }


}
