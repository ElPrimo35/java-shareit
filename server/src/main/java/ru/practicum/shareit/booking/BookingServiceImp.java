package ru.practicum.shareit.booking;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
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
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookingServiceImp implements BookingService {
    private final BookingMapper bookingMapper;
    private final BookingRepository bookingRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;


    @Override
    @Transactional
    public BookingResponseDto createBooking(BookingDto bookingDto, Integer userId) {
        Item item = itemRepository.findById(bookingDto.getItemId()).orElseThrow(() -> new NotFoundException("Item not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        if (!item.getAvailable()) {
            throw new RuntimeException("Item is not available");
        }
        if (bookingDto.getEnd().isBefore(bookingDto.getStart()) || bookingDto.getStart().isEqual(bookingDto.getEnd())) {
            throw new RuntimeException("end should be after start");
        }
        Booking booking = bookingRepository.save(bookingMapper.toBooking(bookingDto, item, Status.WAITING, user));
        return bookingMapper.toResponseDto(booking);
    }

    @Override
    @Transactional
    public BookingResponseDto confirmationBooking(Integer bookingId, boolean approved, Integer userId) {
        Optional<Booking> booking1 = bookingRepository.findBookingByIdAndOwnerId(bookingId, userId);
        if (booking1.isEmpty()) {
            throw new RuntimeException();
        }
        Booking booking = bookingRepository.findBookingByIdAndOwnerId(bookingId, userId)
                .filter(b -> b.getStart().isAfter(LocalDateTime.now()))
                .orElseThrow(() -> new NotFoundException("Бронирование не найдено или уже началось"));
        booking.setStatus(approved ? Status.APPROVED : Status.REJECTED);
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        bookingRepository.save(booking);
        return bookingMapper.toResponseDto(booking);
    }

    @Override
    public BookingResponseDto getBookingById(Integer bookingId, Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        return bookingRepository.findById(bookingId)
                .filter(b -> Objects.equals(b.getBooker().getId(), userId) || Objects.equals(b.getItem().getOwner().getId(), userId))
                .map(bookingMapper::toResponseDto)
                .orElseThrow(() -> new NotFoundException("Booking with id=" + bookingId + " not found"));
    }

    @Override
    public List<BookingResponseDto> getAllBookings(BookingState state, Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        List<BookingResponseDto> bookingDtos = null;
        Sort newestFirst = Sort.by(Sort.Direction.DESC, "start");
        switch (state) {
            case ALL -> {
                bookingDtos = bookingRepository.findBookingsByBooker_Id(userId, newestFirst)
                        .stream().map(bookingMapper::toResponseDto).toList();
            }
            case PAST -> {
                bookingDtos = bookingRepository.findByBooker_IdAndEndIsBefore(userId, LocalDateTime.now(), newestFirst)
                        .stream().map(bookingMapper::toResponseDto).toList();
            }
            case FUTURE -> {
                bookingDtos = bookingRepository.findByBooker_IdAndStartIsAfter(userId, LocalDateTime.now(), newestFirst)
                        .stream().map(bookingMapper::toResponseDto).toList();
            }
            case CURRENT -> {
                bookingDtos = bookingRepository.findByBookerIdAndStartBeforeAndEndAfter(userId, LocalDateTime.now(), newestFirst)
                        .stream().map(bookingMapper::toResponseDto).toList();
            }
            case WAITING -> {
                bookingDtos = bookingRepository.findByBooker_IdAndStatus(userId, Status.WAITING, newestFirst)
                        .stream().map(bookingMapper::toResponseDto).toList();
            }
            case REJECTED -> {
                bookingDtos = bookingRepository.findByBooker_IdAndStatus(userId, Status.REJECTED, newestFirst)
                        .stream().map(bookingMapper::toResponseDto).toList();
            }
        }
        return bookingDtos;
    }

    @Override
    public List<BookingResponseDto> getOwnerBookings(BookingState state, Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        Sort newestFirst = Sort.by(Sort.Direction.DESC, "start");
        List<BookingResponseDto> bookingDtos = null;
        switch (state) {
            case ALL -> {
                bookingDtos = bookingRepository.findByItem_Owner_Id(userId, newestFirst)
                        .stream().map(bookingMapper::toResponseDto).toList();
            }
            case PAST -> {
                bookingDtos = bookingRepository.findByItem_Owner_IdAndEndIsBefore(userId, LocalDateTime.now(), newestFirst)
                        .stream().map(bookingMapper::toResponseDto).toList();
            }
            case FUTURE -> {
                bookingDtos = bookingRepository.findByItem_Owner_IdAndStartIsAfter(userId, LocalDateTime.now(), newestFirst)
                        .stream().map(bookingMapper::toResponseDto).toList();
            }
            case CURRENT -> {
                bookingDtos = bookingRepository.findByItemOwnerIdAndStartBeforeAndEndAfter(userId, LocalDateTime.now(), newestFirst)
                        .stream().map(bookingMapper::toResponseDto).toList();
            }
            case WAITING -> {
                bookingDtos = bookingRepository.findByItem_Owner_IdAndStatus(userId, Status.WAITING, newestFirst)
                        .stream().map(bookingMapper::toResponseDto).toList();
            }
            case REJECTED -> {
                bookingDtos = bookingRepository.findByItem_Owner_IdAndStatus(userId, Status.REJECTED, newestFirst)
                        .stream().map(bookingMapper::toResponseDto).toList();
            }
        }
        boolean isUserWrong = bookingDtos.stream().anyMatch(bookingSecondDto -> !Objects.equals(bookingSecondDto.getBooker().getId(), userId));
        if (!isUserWrong) {
            throw new RuntimeException("Wrong user");
        }
        return bookingDtos;
    }
}
