package ru.practicum.shareit.booking;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.BookingResponseDto;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.item.ItemRepository;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class BookingServiceImp implements BookingService {
    private final BookingMapper bookingMapper;
    private final BookingRepository bookingRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;


    @Override
    public BookingResponseDto createBooking(BookingDto bookingDto, Integer userId) {
        Item item = itemRepository.findById(bookingDto.getItemId()).orElseThrow(() -> new NotFoundException("Item not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        if (!item.getAvailable()) {
            throw new RuntimeException("Item is not available");
        }
        if (bookingDto.getEnd().isBefore(bookingDto.getStart()) || bookingDto.getStart().isEqual(bookingDto.getEnd())) {
            throw new RuntimeException("end should be after start");
        }
        if (bookingDto.getStart().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("start cannot be in past");
        }
        Booking booking = bookingRepository.save(bookingMapper.toBooking(bookingDto, item, Status.WAITING, user));
        return bookingMapper.toResponseDto(booking);
    }

    @Override
    public BookingResponseDto confirmationBooking(Integer bookingId, boolean approved, Integer userId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new NotFoundException("Booking not found"));
        Item item = itemRepository.findById(booking.getItem().getId()).orElseThrow(() -> new NotFoundException("Item not found"));
        if (!Objects.equals(item.getOwner().getId(), userId)) {
            throw new RuntimeException("Only owner can confirm booking");
        }
        booking.setStatus(approved ? Status.APPROVED : Status.REJECTED);
        bookingRepository.save(booking);
        return bookingMapper.toResponseDto(booking);
    }

    @Override
    public BookingResponseDto getBookingById(Integer bookingId, Integer userId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new NotFoundException("booking not found"));
        Item item = itemRepository.findById(booking.getItem().getId()).orElseThrow(() -> new NotFoundException("Item not found"));
        if (!(Objects.equals(booking.getBooker().getId(), userId) || Objects.equals(item.getOwner().getId(), userId))) {
            throw new RuntimeException("Available only for booker or owner");
        }
        return bookingMapper.toResponseDto(booking);
    }

    @Override
    public List<BookingResponseDto> getAllBookings(State state, Integer userId) {
        List<BookingResponseDto> bookingDtos = bookingRepository.findBookingsByUserIdOrderByStartDesc(userId).stream().map(bookingMapper::toResponseDto).toList();
        return stateHandler(state, bookingDtos);
    }

    @Override
    public List<BookingResponseDto> getOwnerBookings(State state, Integer userId) {

        List<Integer> ownerItemIds = itemRepository.findByUserId(userId).stream().map(Item::getId).toList();

        List<BookingResponseDto> bookingDtos = bookingRepository.findBookingsByItemIdsOrderByStartDesc(ownerItemIds).stream().map(bookingMapper::toResponseDto).toList();
        boolean isUserWrong = bookingDtos.stream().anyMatch(bookingSecondDto -> !Objects.equals(bookingSecondDto.getBooker().getId(), userId));
        if (!isUserWrong) {
            throw new RuntimeException("Wrong user");
        }
        return stateHandler(state, bookingDtos);
    }

    private List<BookingResponseDto> stateHandler(State state, List<BookingResponseDto> bookingDtos) {
        if (state == null) {
            state = State.All;
        }
        switch (state) {
            case All -> {
                return bookingDtos;
            }
            case CURRENT -> {
                return bookingDtos.stream().filter(bookingSecondDto -> LocalDateTime.now().isAfter(bookingSecondDto.getStart()) && LocalDateTime.now().isBefore(bookingSecondDto.getEnd())).toList();
            }
            case PAST -> {
                return bookingDtos.stream().filter(bookingSecondDto -> LocalDateTime.now().isAfter(bookingSecondDto.getEnd())).toList();
            }
            case FUTURE -> {
                return bookingDtos.stream().filter(bookingSecondDto -> LocalDateTime.now().isBefore(bookingSecondDto.getStart())).toList();
            }
            case WAITING -> {
                return bookingDtos.stream().filter(bookingSecondDto -> bookingSecondDto.getStatus().equals(Status.WAITING)).toList();
            }
            case REJECTED -> {
                return bookingDtos.stream().filter(bookingSecondDto -> bookingSecondDto.getStatus().equals(Status.REJECTED)).toList();
            }
        }
        return bookingDtos;
    }
}
