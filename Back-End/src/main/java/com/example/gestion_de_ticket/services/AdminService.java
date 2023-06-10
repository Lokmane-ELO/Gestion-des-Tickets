package com.example.gestion_de_ticket.services;



import com.example.gestion_de_ticket.models.Admin;
import com.example.gestion_de_ticket.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {



    @Autowired
    private AdminRepository adminRepository;

    /*public Admin loginAdmin(Long codeUser, String password) {
        return adminRepository.findByCodeUserAndPassword(codeUser, password).orElse(null);
    }*/
    public Admin loginAdmin(String nom_prenom, String password) {
        return adminRepository.findByNomPrenomAndPassword(nom_prenom, password).orElse(null);
    }
    public Admin createAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    public List<Admin> getAdmins() {
        return adminRepository.findAll();
    }

    public Admin getAdminById(Long id) {
        return adminRepository.findById(id).orElse(null);
    }

    public Admin updateAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }
}
