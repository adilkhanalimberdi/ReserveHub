package com.alimberdi.reservehub.repository;

import com.alimberdi.reservehub.domain.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepo extends JpaRepository<Booking, Long> {

    List<Booking> getBookingsByUserEmail(String email);

}
