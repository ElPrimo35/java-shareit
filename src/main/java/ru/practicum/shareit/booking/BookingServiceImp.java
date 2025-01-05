package ru.practicum.shareit.booking;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.booking.dto.BookingDto;

import java.util.List;

@Service
@AllArgsConstructor
public class BookingServiceImp implements BookingService {
    private final BookingStorage bookingStorage;
    private final BookingMapper bookingMapper;

    @Override
    public BookingDto createBooking(BookingDto bookingDto) {
        Booking booking = bookingStorage.createBooking(bookingDto);
        return bookingMapper.toDto(booking, booking.getId());
    }

    @Override
    public BookingDto getBookingDto(Integer id) {
        Booking booking = bookingStorage.getBookingDto(id);
        return bookingMapper.toDto(booking, booking.getId());
    }

    @Override
    public List<BookingDto> getAllBookings() {
        List<Booking> bookingList = bookingStorage.getAllBookings();
        return bookingList.stream()
                .map(booking -> bookingMapper.toDto(booking, booking.getId()))
                .toList();
    }

    @Override
    public BookingDto updateBooking(BookingDto bookingDto, Integer id) {
        Booking booking = bookingStorage.updateBooking(bookingDto, id);
        return bookingMapper.toDto(booking, booking.getId());
    }

    @Override
    public BookingDto deleteBooking(Integer id) {
        Booking booking = bookingStorage.deleteBooking(id);
        return bookingMapper.toDto(booking, booking.getId());
    }
}
