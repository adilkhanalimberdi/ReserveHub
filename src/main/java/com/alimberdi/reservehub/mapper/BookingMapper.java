package com.alimberdi.reservehub.mapper;

import com.alimberdi.reservehub.domain.dto.BookingDTO;
import com.alimberdi.reservehub.domain.entity.Booking;

public class BookingMapper {

    private UserMapper userMapper;
    private ResourceMapper resourceMapper;

    public static BookingDTO toDTO(Booking booking) {
        return new BookingDTO(
                booking.getId(),
                UserMapper.toDTO(booking.getUser()),
                ResourceMapper.toDTO(booking.getResource()),
                booking.getStartTime(),
                booking.getEndTime(),
                booking.getStatus());
    }

}
