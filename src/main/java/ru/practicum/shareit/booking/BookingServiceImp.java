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
        Booking booking = bookingRepository.findBookingByIdAndOwnerId(bookingId, userId);

        booking.setStatus(approved ? Status.APPROVED : Status.REJECTED);
        bookingRepository.save(booking);
        return bookingMapper.toResponseDto(booking);
    }

    @Override
    public BookingResponseDto getBookingById(Integer bookingId, Integer userId) {
        Booking booking = bookingRepository.findBookingByIdAndOwnerId(bookingId, userId);
        Booking booking1 = bookingRepository.findBookingByIdAndBooker_Id(bookingId, userId);
        if (booking != null) {
            return bookingMapper.toResponseDto(booking);
        }
        return bookingMapper.toResponseDto(booking1);
    }

    @Override
    public List<BookingResponseDto> getAllBookings(State state, Integer userId) {
        Sort newestFirst = Sort.by(Sort.Direction.DESC, "start");
        List<BookingResponseDto> bookingDtos = stateHandler(state, bookingRepository.findBookingsByBooker_Id(userId, newestFirst).stream().map(bookingMapper::toResponseDto).toList());
        return stateHandler(state, bookingDtos);
    }

    @Override
    public List<BookingResponseDto> getOwnerBookings(State state, Integer userId) {

        List<Integer> ownerItemIds = itemRepository.findByUserId(userId).stream().map(Item::getId).toList();

        Sort newestFirst = Sort.by(Sort.Direction.DESC, "start");
        List<BookingResponseDto> bookingDtos = stateHandler(state, bookingRepository.findBookingsByItemIds(ownerItemIds, newestFirst).stream().map(bookingMapper::toResponseDto).toList());

        boolean isUserWrong = bookingDtos.stream().anyMatch(bookingSecondDto -> !Objects.equals(bookingSecondDto.getBooker().getId(), userId));
        if (!isUserWrong) {
            throw new RuntimeException("Wrong user");
        }
        return bookingDtos;
    }

    private List<BookingResponseDto> stateHandler(State state, List<BookingResponseDto> bookingResponseDtos) {
        Sort newestFirst = Sort.by(Sort.Direction.DESC, "start");
        if (state == null) {
            state = State.All;
        }
        switch (state) {
            case All -> {
                return bookingResponseDtos;
            }
            case CURRENT -> {
                return bookingRepository.findBookingsByStartBeforeAndEndAfter(LocalDateTime.now(), newestFirst).stream().map(bookingMapper::toResponseDto).toList();
            }
            case PAST -> {
                return bookingRepository.findBookingsByEndBefore(LocalDateTime.now(), newestFirst).stream().map(bookingMapper::toResponseDto).toList();
            }
            case FUTURE -> {
                return bookingRepository.findBookingsByStartAfter(LocalDateTime.now(), newestFirst).stream().map(bookingMapper::toResponseDto).toList();
            }
            case WAITING -> {
                return bookingRepository.findBookingsByStatus(Status.WAITING, newestFirst).stream().map(bookingMapper::toResponseDto).toList();
            }
            case REJECTED -> {
                return bookingRepository.findBookingsByStatus(Status.REJECTED, newestFirst).stream().map(bookingMapper::toResponseDto).toList();
            }
        }
        return bookingResponseDtos;
    }
}
