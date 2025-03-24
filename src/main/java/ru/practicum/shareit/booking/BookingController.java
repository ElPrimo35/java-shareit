package ru.practicum.shareit.booking;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.BookingResponseDto;

import java.util.List;

@RestController
@RequestMapping(path = "/bookings")
@AllArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @PostMapping
    public BookingResponseDto createBooking(@Valid @RequestBody BookingDto bookingDto, @RequestHeader("X-Sharer-User-Id") Integer userId) {
        return bookingService.createBooking(bookingDto, userId);
    }

    @PatchMapping("/{bookingId}")
    public BookingResponseDto confirmationBooking(@PathVariable Integer bookingId, @RequestParam boolean approved, @RequestHeader("X-Sharer-User-Id") Integer userId) {
        return bookingService.confirmationBooking(bookingId, approved, userId);
    }

    @GetMapping("/{bookingId}")
    public BookingResponseDto getBookingById(@PathVariable Integer bookingId, @RequestHeader("X-Sharer-User-Id") Integer userId) {
        return bookingService.getBookingById(bookingId, userId);
    }

    @GetMapping
    public List<BookingResponseDto> getAllBookings(@RequestParam(required = false) State state, @RequestHeader("X-Sharer-User-Id") Integer userId) {
        return bookingService.getAllBookings(state, userId);
    }

    @GetMapping("/owner")
    public List<BookingResponseDto> getOwnerBookings(@RequestParam(required = false) State state, @RequestHeader("X-Sharer-User-Id") Integer userId) {
        return bookingService.getOwnerBookings(state, userId);
    }
}
