package com.alimberdi.reservehub.domain.dto;

import com.alimberdi.reservehub.domain.enums.UserRole;

public record UserDTO (
    Long id,
    String name,
    String email,
    UserRole role
) {}
