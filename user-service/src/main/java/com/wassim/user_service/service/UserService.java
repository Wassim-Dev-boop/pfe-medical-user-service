package com.wassim.user_service.service;

import com.wassim.user_service.entity.User;
import com.wassim.user_service.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repo;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    public User create(User user) {
        user.setMotDePasse(passwordEncoder.encode(user.getMotDePasse()));
        return repo.save(user);
    }
}
