package com.wassim.user_service.exception;

public class EmailAlreadyUsedException extends RuntimeException {
    public EmailAlreadyUsedException(String email) {
        super("Email déjà utilisé: " + email);
    }
}
