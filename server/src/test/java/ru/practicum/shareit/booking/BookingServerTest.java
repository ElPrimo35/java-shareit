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

    UserDto getUser9() {
        UserDto user = new UserDto();
        user.setId(9);
        user.setName("Test8");
        user.setEmail("Test8@Test");
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

    UserDto getUser12() {
        UserDto user = new UserDto();
        user.setId(12);
        user.setName("Test11");
        user.setEmail("Test11@Test");
        return user;
    }

    UserDto getUser13() {
        UserDto user = new UserDto();
        user.setId(13);
        user.setName("Test12");
        user.setEmail("Test12@Test");
        return user;
    }

    UserDto getUser14() {
        UserDto user = new UserDto();
        user.setId(14);
        user.setName("Test13");
        user.setEmail("Test13@Test");
        return user;
    }


    UserDto getUser16() {
        UserDto user = new UserDto();
        user.setId(16);
        user.setName("Test15");
        user.setEmail("Test15@Test");
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

    ItemDto getItem5() {
        ItemDto item = new ItemDto();
        item.setId(5);
        item.setName("Test4");
        item.setDescription("Test4");
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

    BookingResponseDto getCurrentBooking() {
        BookingResponseDto booking = new BookingResponseDto();
        booking.setId(7);
        booking.setStart(LocalDateTime.of(2023, 12, 12, 0, 0, 0));
        booking.setEnd(LocalDateTime.of(2027, 12, 12, 0, 0, 0));
        booking.setItem(getItem4());
        booking.setBooker(getUser9());
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

    BookingResponseDto getAllOwnerBooking() {
        BookingResponseDto booking = new BookingResponseDto();
        booking.setId(10);
        booking.setStart(LocalDateTime.of(2023, 12, 12, 0, 0, 0));
        booking.setEnd(LocalDateTime.of(2024, 12, 12, 0, 0, 0));
        booking.setItem(getItem5());
        booking.setBooker(getUser12());
        booking.setStatus(Status.APPROVED);
        return booking;
    }

    BookingResponseDto getPastOwnerBooking() {
        BookingResponseDto booking = new BookingResponseDto();
        booking.setId(11);
        booking.setStart(LocalDateTime.of(2023, 12, 12, 0, 0, 0));
        booking.setEnd(LocalDateTime.of(2024, 12, 12, 0, 0, 0));
        booking.setItem(getItem5());
        booking.setBooker(getUser13());
        booking.setStatus(Status.APPROVED);
        return booking;
    }

    BookingResponseDto getFutureOwnerBooking() {
        BookingResponseDto booking = new BookingResponseDto();
        booking.setId(12);
        booking.setStart(LocalDateTime.of(2026, 12, 12, 0, 0, 0));
        booking.setEnd(LocalDateTime.of(2027, 12, 12, 0, 0, 0));
        booking.setItem(getItem5());
        booking.setBooker(getUser14());
        booking.setStatus(Status.APPROVED);
        return booking;
    }

    BookingResponseDto getCurrentOwnerBooking() {
        BookingResponseDto booking = new BookingResponseDto();
        booking.setId(13);
        booking.setStart(LocalDateTime.of(2023, 12, 12, 0, 0, 0));
        booking.setEnd(LocalDateTime.of(2027, 12, 12, 0, 0, 0));
        booking.setItem(getItem5());
        booking.setBooker(getUser14());
        booking.setStatus(Status.APPROVED);
        return booking;
    }

    BookingResponseDto getWaitingOwnerBooking() {
        BookingResponseDto booking = new BookingResponseDto();
        booking.setId(14);
        booking.setStart(LocalDateTime.of(2023, 12, 12, 0, 0, 0));
        booking.setEnd(LocalDateTime.of(2027, 12, 12, 0, 0, 0));
        booking.setItem(getItem5());
        booking.setBooker(getUser14());
        booking.setStatus(Status.WAITING);
        return booking;
    }

    BookingResponseDto getRejectedOwnerBooking() {
        BookingResponseDto booking = new BookingResponseDto();
        booking.setId(15);
        booking.setStart(LocalDateTime.of(2023, 12, 12, 0, 0, 0));
        booking.setEnd(LocalDateTime.of(2027, 12, 12, 0, 0, 0));
        booking.setItem(getItem5());
        booking.setBooker(getUser14());
        booking.setStatus(Status.REJECTED);
        return booking;
    }

    BookingResponseDto getBooking16() {
        BookingResponseDto booking = new BookingResponseDto();
        booking.setId(16);
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
    void getCurrentBookings() {
        List<BookingResponseDto> bookings = new ArrayList<>();
        bookings.add(getCurrentBooking());
        List<BookingResponseDto> bookingList = bookingService.getAllBookings(BookingState.CURRENT, 9);
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
    void getOwnerBookingsAllTest() {
        List<BookingResponseDto> bookings = new ArrayList<>();
        bookings.add(getFutureOwnerBooking());
        bookings.add(getAllOwnerBooking());
        bookings.add(getPastOwnerBooking());
        bookings.add(getCurrentOwnerBooking());
        bookings.add(getWaitingOwnerBooking());
        bookings.add(getRejectedOwnerBooking());
        List<BookingResponseDto> bookingList = bookingService.getOwnerBookings(BookingState.ALL, 5);
        Assertions.assertEquals(bookings, bookingList);
    }


    @Test
    void getOwnerBookingsPastTest() {
        List<BookingResponseDto> bookings = new ArrayList<>();
        bookings.add(getAllOwnerBooking());
        bookings.add(getPastOwnerBooking());
        List<BookingResponseDto> bookingList = bookingService.getOwnerBookings(BookingState.PAST, 5);
        Assertions.assertEquals(bookings, bookingList);
    }

    @Test
    void getOwnerBookingFutureTest() {
        List<BookingResponseDto> bookings = new ArrayList<>();
        bookings.add(getFutureOwnerBooking());
        List<BookingResponseDto> bookingList = bookingService.getOwnerBookings(BookingState.FUTURE, 5);
        Assertions.assertEquals(bookings, bookingList);
    }

    @Test
    void getOwnerBookingCurrentTest() {
        List<BookingResponseDto> bookings = new ArrayList<>();
        bookings.add(getCurrentOwnerBooking());
        bookings.add(getWaitingOwnerBooking());
        bookings.add(getRejectedOwnerBooking());
        List<BookingResponseDto> bookingList = bookingService.getOwnerBookings(BookingState.CURRENT, 5);
        Assertions.assertEquals(bookings, bookingList);
    }

    @Test
    void getOwnerBookingWaitingTest() {
        List<BookingResponseDto> bookings = new ArrayList<>();
        bookings.add(getWaitingOwnerBooking());
        List<BookingResponseDto> bookingList = bookingService.getOwnerBookings(BookingState.WAITING, 5);
        Assertions.assertEquals(bookings, bookingList);
    }

    @Test
    void getOwnerBookingRejectedTest() {
        List<BookingResponseDto> bookings = new ArrayList<>();
        bookings.add(getRejectedOwnerBooking());
        List<BookingResponseDto> bookingList = bookingService.getOwnerBookings(BookingState.REJECTED, 5);
        Assertions.assertEquals(bookings, bookingList);
    }

    @Test
    void createBookingTest() {
        BookingResponseDto bookingResponseDto = getBooking16();
        BookingResponseDto bookingResponseDtoTest = bookingService.createBooking(getBookingDto6(), 5);

        Assertions.assertEquals(bookingResponseDto, bookingResponseDtoTest);
    }
}
