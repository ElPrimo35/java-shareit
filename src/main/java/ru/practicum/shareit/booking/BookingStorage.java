package ru.practicum.shareit.booking;

import ru.practicum.shareit.booking.dto.BookingDto;

import java.util.List;

public interface BookingStorage {
    Booking createBooking(BookingDto bookingDto);

    Booking getBookingDto(Integer id);

    List<Booking> getAllBookings();

    Booking updateBooking(BookingDto bookingDto, Integer id);

    Booking deleteBooking(Integer id);
}
