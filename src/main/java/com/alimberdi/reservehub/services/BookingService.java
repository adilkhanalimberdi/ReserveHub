package com.alimberdi.reservehub.services;

import com.alimberdi.reservehub.domain.dto.BookingDTO;
import com.alimberdi.reservehub.domain.entity.Booking;
import com.alimberdi.reservehub.domain.entity.Resource;
import com.alimberdi.reservehub.domain.entity.User;
import com.alimberdi.reservehub.mapper.BookingMapper;
import com.alimberdi.reservehub.repository.BookingRepo;
import com.alimberdi.reservehub.repository.ResourceRepo;
import com.alimberdi.reservehub.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepo bookingRepo;
    private final UserRepo userRepo;
    private final ResourceRepo resourceRepo;

    public BookingDTO getBookingById(Long id) {
        return bookingRepo.findById(id)
                .map(BookingMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    public List<BookingDTO> getAllBookings() {
        return bookingRepo.findAll()
                .stream()
                .map(BookingMapper::toDTO)
                .toList();
    }

    public List<BookingDTO> getBookingsByEmail(String email) {
        return bookingRepo.getBookingsByUserEmail(email)
                .stream()
                .map(BookingMapper::toDTO)
                .toList();
    }

    public BookingDTO createBooking(BookingDTO dto) {
        Booking booking = toEntity(dto);

        return BookingMapper.toDTO(booking);
    }

    public BookingDTO updateBooking(Long id, BookingDTO dto) {
        if (!bookingRepo.existsById(id)) {
            throw new RuntimeException("Booking not found");
        }
        Booking booking = toEntity(dto);
        booking.setId(id);
        return BookingMapper.toDTO(bookingRepo.save(booking));
    }

    public BookingDTO updateBooking(Long id, LocalDateTime startTime, LocalDateTime endTime) {
        if (!bookingRepo.existsById(id)) {
            throw new RuntimeException("Booking not found");
        }
        Booking booking = bookingRepo.findById(id).orElseThrow(() ->
                new RuntimeException("Booking not found"));

        booking.setId(id);
        booking.setStartTime(startTime);
        booking.setEndTime(endTime);

        return BookingMapper.toDTO(bookingRepo.save(booking));
    }

    public void deleteBooking(Long id) {
        if (!bookingRepo.existsById(id)) {
            throw new RuntimeException("Booking not found");
        }
        bookingRepo.deleteById(id);
    }

    public Booking toEntity(BookingDTO dto) {
        User user = userRepo.findById(dto.user().id())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Resource resource = resourceRepo.findById(dto.resource().id())
                .orElseThrow(() -> new RuntimeException("Resource not found"));

        Booking booking = new Booking();

        booking.setId(dto.id());
        booking.setUser(user);
        booking.setResource(resource);
        booking.setStartTime(dto.startTime());
        booking.setEndTime(dto.endTime());
        booking.setStatus(dto.status());

        return booking;
    }

}
