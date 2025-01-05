package ru.practicum.shareit.booking;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.booking.dto.BookingDto;

@Component
public class BookingMapper {
    public Booking toBooking(BookingDto bookingDto, Integer id) {
        Booking booking = new Booking();
        booking.setId(id);
        booking.setStart(bookingDto.getStart());
        booking.setEnd(bookingDto.getEnd());
        booking.setItem(bookingDto.getItem());
        booking.setBooker(bookingDto.getBooker());
        booking.setStatus(bookingDto.getStatus());
        return booking;
    }

    public BookingDto toDto(Booking booking, Integer id) {
        BookingDto bookingDto = new BookingDto();
        bookingDto.setId(id);
        bookingDto.setStart(booking.getStart());
        bookingDto.setEnd(booking.getStart());
        bookingDto.setItem(booking.getItem());
        bookingDto.setBooker(booking.getBooker());
        bookingDto.setStatus(booking.getStatus());
        return bookingDto;
    }
}
