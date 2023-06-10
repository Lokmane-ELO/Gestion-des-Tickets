package com.example.gestion_de_ticket.controller;



import com.example.gestion_de_ticket.models.User;
import com.example.gestion_de_ticket.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setCodeUser(id);
        return userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/codeUser")
    public Long getCodeUserByNomAndPrenom(@RequestParam String nom, @RequestParam String prenom) {
        User user = userService.getUserByNomAndPrenom(nom, prenom);
        return user != null ? user.getCodeUser() : null;
    }
}