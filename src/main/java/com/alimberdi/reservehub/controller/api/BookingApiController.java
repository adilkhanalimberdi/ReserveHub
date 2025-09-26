package com.alimberdi.reservehub.controller.api;

import com.alimberdi.reservehub.domain.dto.BookingDTO;
import com.alimberdi.reservehub.services.BookingService;
import lombok.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/bookings")
public class BookingApiController {

    private final BookingService bookingService;

    @GetMapping("/{id}")
    public BookingDTO getBooking(@PathVariable Long id) {
        return bookingService.getBookingById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteResource(@PathVariable Long id) {
        bookingService.deleteBooking(id);
    }

}
