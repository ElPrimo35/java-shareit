package ru.practicum.shareit.booking;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.BookingResponseDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.user.dto.UserDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor(onConstructor_ = @Autowired)
@SpringBootTest
public class BookingServerTest {
    private final BookingService bookingService;


    UserDto getUser5() {
        UserDto user = new UserDto();
        user.setId(5);
        user.setName("Test4");
        user.setEmail("Test4@Test");
        return user;
    }

    UserDto getUser10() {
        UserDto user = new UserDto();
        user.setId(10);
        user.setName("Test9");
        user.setEmail("Test9@Test");
        return user;
    }

    UserDto getUser11() {
        UserDto user = new UserDto();
        user.setId(11);
        user.setName("Test10");
        user.setEmail("Test10@Test");
        return user;
    }

    UserDto getUser16() {
        UserDto user = new UserDto();
        user.setId(16);
        user.setName("Test15");
        user.setEmail("Test15@Test");
        return user;
    }

    UserDto getUser20() {
        UserDto user = new UserDto();
        user.setId(20);
        user.setName("Test20");
        user.setEmail("Test20@Test");
        return user;
    }


    ItemDto getItem3() {
        ItemDto item = new ItemDto();
        item.setId(3);
        item.setName("Test2");
        item.setDescription("Test2");
        item.setAvailable(true);
        item.setRequestId(null);
        return item;
    }

    ItemDto getItem4() {
        ItemDto item = new ItemDto();
        item.setId(4);
        item.setName("Test3");
        item.setDescription("Test3");
        item.setAvailable(true);
        item.setRequestId(null);
        return item;
    }

    ItemDto getItem2() {
        ItemDto item = new ItemDto();
        item.setId(2);
        item.setName("Test1");
        item.setDescription("Test1");
        item.setAvailable(true);
        item.setRequestId(2);
        return item;
    }

    ItemDto getItem8() {
        ItemDto item = new ItemDto();
        item.setId(8);
        item.setName("Test8");
        item.setDescription("Test8");
        item.setAvailable(true);
        item.setRequestId(null);
        return item;
    }


    BookingResponseDto getBooking() {
        BookingResponseDto booking = new BookingResponseDto();
        booking.setId(1);
        booking.setStart(LocalDateTime.of(2022, 12, 12, 0, 0, 0));
        booking.setEnd(LocalDateTime.of(2023, 12, 12, 0, 0, 0));
        booking.setItem(getItem3());
        booking.setBooker(getUser5());
        booking.setStatus(Status.APPROVED);
        return booking;
    }

    BookingResponseDto getFutureBooking() {
        BookingResponseDto booking = new BookingResponseDto();
        booking.setId(6);
        booking.setStart(LocalDateTime.of(2026, 12, 12, 0, 0, 0));
        booking.setEnd(LocalDateTime.of(2027, 12, 12, 0, 0, 0));
        booking.setItem(getItem4());
        booking.setBooker(getUser16());
        booking.setStatus(Status.APPROVED);
        return booking;
    }

    BookingResponseDto getWaitingBooking() {
        BookingResponseDto booking = new BookingResponseDto();
        booking.setId(8);
        booking.setStart(LocalDateTime.of(2023, 12, 12, 0, 0, 0));
        booking.setEnd(LocalDateTime.of(2027, 12, 12, 0, 0, 0));
        booking.setItem(getItem4());
        booking.setBooker(getUser10());
        booking.setStatus(Status.WAITING);
        return booking;
    }

    BookingResponseDto getRejectedBooking() {
        BookingResponseDto booking = new BookingResponseDto();
        booking.setId(9);
        booking.setStart(LocalDateTime.of(2023, 12, 12, 0, 0, 0));
        booking.setEnd(LocalDateTime.of(2027, 12, 12, 0, 0, 0));
        booking.setItem(getItem4());
        booking.setBooker(getUser11());
        booking.setStatus(Status.REJECTED);
        return booking;
    }

    BookingResponseDto getBooking18() {
        BookingResponseDto booking = new BookingResponseDto();
        booking.setStart(LocalDateTime.of(2025, 12, 12, 0, 0, 0));
        booking.setEnd(LocalDateTime.of(2026, 12, 12, 0, 0, 0));
        booking.setItem(getItem2());
        booking.setBooker(getUser5());
        booking.setStatus(Status.WAITING);
        return booking;
    }

    BookingDto getBookingDto6() {
        BookingDto bookingDto = new BookingDto();
        bookingDto.setId(6);
        bookingDto.setStart(LocalDateTime.of(2025, 12, 12, 0, 0, 0));
        bookingDto.setEnd(LocalDateTime.of(2026, 12, 12, 0, 0, 0));
        bookingDto.setItemId(2);
        return bookingDto;
    }

    BookingDto getBookingException() {
        BookingDto bookingDto = new BookingDto();
        bookingDto.setId(6);
        bookingDto.setStart(LocalDateTime.of(2025, 12, 12, 0, 0, 0));
        bookingDto.setEnd(LocalDateTime.of(2026, 12, 12, 0, 0, 0));
        bookingDto.setItemId(7);
        return bookingDto;
    }


    BookingDto getBookingExceptionStartAfterEnd() {
        BookingDto bookingDto = new BookingDto();
        bookingDto.setId(6);
        bookingDto.setStart(LocalDateTime.of(2025, 12, 12, 0, 0, 0));
        bookingDto.setEnd(LocalDateTime.of(2024, 12, 12, 0, 0, 0));
        bookingDto.setItemId(7);
        return bookingDto;
    }


    @Test
    void createNotAvailableBookingTest() {
        Assertions.assertThrows(RuntimeException.class, () -> bookingService.createBooking(getBookingException(), 1));
    }

    @Test
    void createStartAfterEndBookingTest() {
        Assertions.assertThrows(RuntimeException.class, () -> bookingService.createBooking(getBookingExceptionStartAfterEnd(), 1));
    }

    @Test
    void getBookingByIdTest() {
        BookingResponseDto bookingResponseDtoTest = getBooking();
        BookingResponseDto bookingResponseDto = bookingService.getBookingById(1, 3);
        Assertions.assertEquals(bookingResponseDtoTest, bookingResponseDto);
    }


    @Test
    void getAllBookingsPastTest() {
        List<BookingResponseDto> bookings = new ArrayList<>();
        bookings.add(getBooking());
        List<BookingResponseDto> bookingList = bookingService.getAllBookings(BookingState.PAST, getUser5().getId());
        Assertions.assertEquals(bookings, bookingList);
    }


    @Test
    void getAllBookingsFutureTest() {
        List<BookingResponseDto> bookings = new ArrayList<>();
        bookings.add(getFutureBooking());
        List<BookingResponseDto> bookingList = bookingService.getAllBookings(BookingState.FUTURE, 16);
        Assertions.assertEquals(bookings, bookingList);
    }


    @Test
    void getAllBookingsTest() {
        List<BookingResponseDto> bookings = new ArrayList<>();
        bookings.add(getFutureBooking());
        List<BookingResponseDto> bookingList = bookingService.getAllBookings(BookingState.ALL, 16);
        Assertions.assertEquals(bookings, bookingList);
    }


    @Test
    void getWaitingBookings() {
        List<BookingResponseDto> bookings = new ArrayList<>();
        bookings.add(getWaitingBooking());
        List<BookingResponseDto> bookingList = bookingService.getAllBookings(BookingState.WAITING, 10);
        Assertions.assertEquals(bookings, bookingList);
    }


    @Test
    void getRejectedBookings() {
        List<BookingResponseDto> bookings = new ArrayList<>();
        bookings.add(getRejectedBooking());
        List<BookingResponseDto> bookingList = bookingService.getAllBookings(BookingState.REJECTED, 11);
        Assertions.assertEquals(bookings, bookingList);
    }


    @Test
    void confirmationBookingsTest() {
        BookingResponseDto bookingResponseDto = bookingService.createBooking(getBookingDto6(), 5);
        BookingResponseDto bookingResponseDto1 = bookingService.confirmationBooking(bookingResponseDto.getId(), true, 2);
        Assertions.assertEquals(Status.APPROVED, bookingResponseDto1.getStatus());
    }

    @Test
    void confirmationExceptionBookingsTest() {
        Assertions.assertThrows(RuntimeException.class, () -> bookingService.confirmationBooking(100, true, 100));
    }

    @Test
    void createBookingTest() {
        BookingResponseDto bookingResponseDto = getBooking18();
        BookingResponseDto bookingResponseDtoTest = bookingService.createBooking(getBookingDto6(), 5);

        bookingResponseDtoTest.setId(null);


        Assertions.assertEquals(bookingResponseDto, bookingResponseDtoTest);
    }

    @Test
    void getOwnerBookingsTest() {
        UserDto userDto = getUser20();
        BookingDto booking = new BookingDto();
        booking.setStart(LocalDateTime.of(2025, 12, 12, 0, 0, 0));
        booking.setEnd(LocalDateTime.of(2026, 12, 12, 0, 0, 0));
        booking.setItemId(getItem8().getId());
        BookingResponseDto bookingResponseDto = bookingService.createBooking(booking, userDto.getId());


        Assertions.assertEquals(List.of(bookingResponseDto), bookingService.getOwnerBookings(BookingState.WAITING, 21));
        Assertions.assertEquals(List.of(bookingResponseDto), bookingService.getOwnerBookings(null, 21));
        Assertions.assertEquals(List.of(bookingResponseDto), bookingService.getOwnerBookings(BookingState.FUTURE, 21));
        Assertions.assertThrows(RuntimeException.class, () -> bookingService.getOwnerBookings(BookingState.PAST, 21));
        Assertions.assertThrows(RuntimeException.class, () -> bookingService.getOwnerBookings(BookingState.CURRENT, 21));
        Assertions.assertThrows(RuntimeException.class, () -> bookingService.getOwnerBookings(BookingState.REJECTED, 21));
    }
}
