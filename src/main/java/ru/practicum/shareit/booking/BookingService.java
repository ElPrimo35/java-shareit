package ru.practicum.shareit.booking;

import ru.practicum.shareit.booking.dto.BookingDto;

import java.util.List;

public interface BookingService {
    BookingDto createBooking(BookingDto bookingDto);

    BookingDto getBookingDto(Integer id);

    List<BookingDto> getAllBookings();

    BookingDto updateBooking(BookingDto bookingDto, Integer id);

    BookingDto deleteBooking(Integer id);
}
