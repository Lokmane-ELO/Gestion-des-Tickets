package com.example.gestion_de_ticket.controller;

import com.example.gestion_de_ticket.models.Admin;
import com.example.gestion_de_ticket.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admins")
public class AdminController {
    @Autowired
    private AdminService adminService;


    /*@PostMapping("/login")
    public ResponseEntity<Admin> loginAdmin(@RequestBody Admin admin) {
        Admin loggedAdmin = adminService.loginAdmin(admin.getCodeUser(), admin.getPassword());
        if (loggedAdmin == null) {
            // Authentification a échoué
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            // Authentification réussie
            return new ResponseEntity<>(loggedAdmin, HttpStatus.OK);
        }
    }*/


    @PostMapping
    public Admin createAdmin(@RequestBody Admin admin) {
        return adminService.createAdmin(admin);
    }

    @GetMapping
    public List<Admin> getAdmins() {
        return adminService.getAdmins();
    }

    @GetMapping("/{id}")
    public Admin getAdminById(@PathVariable Long id) {
        return adminService.getAdminById(id);
    }

    @PutMapping("/{id}")
    public Admin updateAdmin(@PathVariable Long id, @RequestBody Admin admin) {
        admin.setCodeUser(id);
        return adminService.updateAdmin(admin);
    }

    @DeleteMapping("/{id}")
    public void deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);
    }

    @PostMapping("/login")
    public ResponseEntity<Admin> loginAdmin(@RequestBody Map<String, String> body) {
        String nom_prenom = body.get("nom_prenom").replace("_", " ");  // Remplacer l'underscore par un espace
        String password = body.get("password");

        Admin foundAdmin = adminService.loginAdmin(nom_prenom, password);

        if (foundAdmin == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // renvoie 401 si l'administrateur n'est pas trouvé
        }

        return new ResponseEntity<>(foundAdmin, HttpStatus.OK); // renvoie 200 et l'administrateur si l'administrateur est trouvé
    }

}