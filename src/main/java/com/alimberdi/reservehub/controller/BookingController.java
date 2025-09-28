package com.alimberdi.reservehub.controller;

import com.alimberdi.reservehub.domain.dto.BookingDTO;
import com.alimberdi.reservehub.domain.dto.UserDTO;
import com.alimberdi.reservehub.domain.entity.Booking;
import com.alimberdi.reservehub.domain.enums.BookingStatus;
import com.alimberdi.reservehub.services.BookingService;
import com.alimberdi.reservehub.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;
    private final UserService userService;

    @GetMapping("/my")
    public String myBookings(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        UserDTO user = userService.getUserByEmail(userDetails.getUsername());
        List<BookingDTO> bookings = bookingService.getBookingsByEmail(user.email());

        model.addAttribute("name", user.name());
        model.addAttribute("email", user.email());

        LocalDateTime now = LocalDateTime.now();

        List<BookingDTO> upcoming = new ArrayList<>();
        List<BookingDTO> archive = new ArrayList<>();
        List<BookingDTO> ongoing = new ArrayList<>();

        for (BookingDTO booking : bookings) {
            if (now.isBefore(booking.startTime()) && !booking.status().equals(BookingStatus.CANCELED)) {
                upcoming.add(booking);
            } else if (now.isAfter(booking.endTime()) || booking.status().equals(BookingStatus.CANCELED)) {
                archive.add(booking);
            } else {
                ongoing.add(booking);
            }
        }

        Collections.reverse(upcoming);
        Collections.reverse(archive);

        model.addAttribute("upcoming", upcoming);
        model.addAttribute("archive", archive);
        model.addAttribute("ongoing", ongoing);

        return "my-bookings";
    }

    @PostMapping("/confirm/{id}")
    public String confirmBooking(@PathVariable Long id) {
        BookingDTO dto = bookingService.getBookingById(id);
        BookingDTO newDto = new BookingDTO(
                dto.id(),
                dto.user(),
                dto.resource(),
                dto.startTime(),
                dto.endTime(),
                BookingStatus.CONFIRMED
        );

        bookingService.updateBooking(id, newDto);
        return "redirect:/bookings/my";
    }

    @PostMapping("/cancel/{id}")
    public String cancelBooking(@PathVariable Long id) {
        BookingDTO dto = bookingService.getBookingById(id);
        BookingDTO newDto = new BookingDTO(
                dto.id(),
                dto.user(),
                dto.resource(),
                dto.startTime(),
                dto.endTime(),
                BookingStatus.CANCELED
        );
        bookingService.updateBooking(id, newDto);
        return "redirect:/bookings/my";
    }

    @PostMapping("/update/{id}")
    public String updateBooking(
            @PathVariable Long id,
            @RequestParam LocalDateTime startTime,
            @RequestParam LocalDateTime endTime
    ) {
        LocalDateTime now = LocalDateTime.now();

        if (!now.isAfter(startTime) && startTime.isBefore(endTime)) {
            bookingService.updateBooking(id, startTime, endTime);
        }
        return "redirect:/bookings/my";
    }

    @PostMapping("/delete/{id}")
    public String deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return "redirect:/bookings/my";
    }

}
