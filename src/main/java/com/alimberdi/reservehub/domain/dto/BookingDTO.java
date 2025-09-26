package com.alimberdi.reservehub.domain.dto;

import com.alimberdi.reservehub.domain.enums.BookingStatus;

//import java.time.Instant;
import java.time.LocalDateTime;

public record BookingDTO (
    Long id,
    UserDTO user,
    ResourceDTO resource,
    LocalDateTime startTime,
    LocalDateTime endTime,
    BookingStatus status
) {}