package com.alimberdi.reservehub.domain.dto;

public record ResourceDTO (
    Long id,
    String name,
    String description,
    Long capacity
) {}