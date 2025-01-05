package ru.practicum.shareit.booking;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.exception.NotFoundException;

import java.util.HashMap;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BookingStorageImp implements BookingStorage {
    private final HashMap<Integer, Booking> bookings = new HashMap<>();
    private Integer bookingIdGenerator = 1;
    private final BookingMapper bookingMapper;

    @Override
    public Booking createBooking(BookingDto bookingDto) {
        Booking booking = bookingMapper.toBooking(bookingDto, bookingIdGenerator);
        booking.setStatus(Status.WAITING);
        bookings.put(bookingIdGenerator++, booking);
        return booking;
    }

    @Override
    public Booking getBookingDto(Integer id) {
        return bookings.get(id);
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookings.values().stream().toList();
    }

    @Override
    public Booking updateBooking(BookingDto bookingDto, Integer id) {
        if (bookings.get(id) == null) {
            throw new NotFoundException("Booking not found");
        }
        Booking booking = bookingMapper.toBooking(bookingDto, id);
        bookings.put(id, booking);
        return booking;
    }

    @Override
    public Booking deleteBooking(Integer id) {
        if (bookings.get(id) == null) {
            throw new NotFoundException("Booking not found");
        }
        Booking booking = bookings.get(id);
        bookings.remove(id);
        return booking;
    }
}
