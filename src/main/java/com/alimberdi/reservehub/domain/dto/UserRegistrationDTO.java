package com.alimberdi.reservehub.domain.dto;

public record UserRegistrationDTO(
        String name,
        String email,
        String password
) {}
