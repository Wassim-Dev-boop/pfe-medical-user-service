package com.wassim.user_service.controller;

import com.wassim.user_service.entity.User;
import com.wassim.user_service.repository.UserRepository;
import com.wassim.user_service.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rôle (Controller REST) :
 * - Expose les endpoints HTTP du User Service.
 * - Ne contient pas la logique métier (encodage mot de passe, règles, etc.) -> c'est le rôle du UserService.
 * - Sert de point d'entrée pour le frontend Angular ou Postman via API REST. [web:59]
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    /**
     * Rôle :
     * - UserRepository : accès direct aux données (lecture simple).
     * - UserService : logique métier (ex: encoder mot de passe avant sauvegarde). [web:252]
     */
    private final UserRepository repo;
    private final UserService userService;

    /**
     * Injection par constructeur (bonne pratique Spring) :
     * Spring fournit automatiquement les dépendances au démarrage. [web:252]
     */
    public UserController(UserRepository repo, UserService userService) {
        this.repo = repo;
        this.userService = userService;
    }

    /**
     * Endpoint de test pour vérifier que le service répond.
     */
    @GetMapping("/ping")
    public String ping() {
        return "OK";
    }

    /**
     * GET /api/users
     * Rôle : retourner la liste des utilisateurs (utile pour tester rapidement).
     * (Plus tard : pagination + DTO + sécurité). [web:262]
     */
    @GetMapping
    public List<User> getAll() {
        return repo.findAll();
    }

    /**
     * POST /api/users
     * Rôle : créer un utilisateur (patient ou médecin).
     * La logique métier (ex: BCrypt) est dans UserService. [web:382][web:380]
     */
    @PostMapping
    public User create(@RequestBody User user) {
        return userService.create(user);
    }
}
