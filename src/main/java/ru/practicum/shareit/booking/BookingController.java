package ru.practicum.shareit.booking;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.booking.dto.BookingDto;

import java.util.List;

/**
 * TODO Sprint add-bookings.
 */
@RestController
@RequestMapping(path = "/bookings")
@AllArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @PostMapping
    public BookingDto createBooking(@RequestBody BookingDto bookingDto) {
        return bookingService.createBooking(bookingDto);
    }

    @GetMapping("/{id}")
    public BookingDto getBookingDto(@PathVariable Integer id) {
        return bookingService.getBookingDto(id);
    }

    @GetMapping
    public List<BookingDto> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @PatchMapping("/{id}")
    public BookingDto updateBooking(@RequestBody BookingDto bookingDto, @PathVariable Integer id) {
        return bookingService.updateBooking(bookingDto, id);
    }

    @DeleteMapping("/{id}")
    public BookingDto deleteBooking(@PathVariable Integer id) {
        return bookingService.deleteBooking(id);
    }
}
