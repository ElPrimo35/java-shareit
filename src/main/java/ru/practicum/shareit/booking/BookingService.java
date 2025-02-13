package ru.practicum.shareit.booking;

import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.BookingResponseDto;

import java.util.List;

public interface BookingService {
    BookingResponseDto createBooking(BookingDto bookingDto, Integer userId);

    BookingResponseDto confirmationBooking(Integer id, boolean approved, Integer userId);

    BookingResponseDto getBookingById(Integer BookingId, Integer userId);

    List<BookingResponseDto> getAllBookings(State state, Integer userId);

    List<BookingResponseDto> getOwnerBookings(State state, Integer userId);
}
