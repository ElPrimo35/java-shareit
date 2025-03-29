package ru.practicum.shareit.booking;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@RequiredArgsConstructor
public class BookingRepositoryTest {
    @Autowired
    private BookingRepository bookingRepository;

    User getUser3() {
        User user = new User();
        user.setId(3);
        user.setName("Test2");
        user.setEmail("Test2@Test");
        return user;
    }

    User getUser5() {
        User user = new User();
        user.setId(5);
        user.setName("Test4");
        user.setEmail("Test4@Test");
        return user;
    }

    Item getItem() {
        Item item = new Item();
        item.setId(3);
        item.setName("Test2");
        item.setDescription("Test2");
        item.setAvailable(true);
        item.setOwner(getUser3());
        item.setRequest(null);
        return item;
    }


    Booking getBooking() {
        Booking booking = new Booking();
        booking.setId(1);
        booking.setStart(LocalDateTime.of(2022, 12, 12, 0, 0, 0));
        booking.setEnd(LocalDateTime.of(2023, 12, 12, 0, 0, 0));
        booking.setItem(getItem());
        booking.setBooker(getUser5());
        booking.setStatus(Status.APPROVED);
        return booking;
    }

    @Test
    void findBookingsByBooker_IdTest() {
        List<Booking> bookingList = new ArrayList<>();
        bookingList.add(getBooking());
        Sort newestFirst = Sort.by(Sort.Direction.DESC, "start");
        List<Booking> bookings = bookingRepository.findBookingsByBooker_Id(5, newestFirst);
        Assertions.assertEquals(bookingList.toString(), bookings.toString());
    }

    @Test
    void findBookingsByItem_IdTest() {
        List<Booking> bookingList = new ArrayList<>();
        bookingList.add(getBooking());
        Sort newestFirst = Sort.by(Sort.Direction.DESC, "start");
        List<Booking> bookings = bookingRepository.findBookingsByItem_Id(3, newestFirst);
        Assertions.assertEquals(bookingList.toString(), bookings.toString());
    }

    @Test
    void findBookingByIdAndOwnerIdTest() {
        Booking booking = bookingRepository.findBookingByIdAndOwnerId(1, 3).get();
        Assertions.assertEquals(booking.toString(), getBooking().toString());
    }

    @Test
    void findByItem_Owner_IdTest() {
        List<Booking> bookings = new ArrayList<>();
        bookings.add(getBooking());
        Sort newestFirst = Sort.by(Sort.Direction.DESC, "start");
        List<Booking> bookingList = bookingRepository.findByItem_Owner_Id(3, newestFirst);
        Assertions.assertEquals(bookings.toString(), bookingList.toString());
    }

    @Test
    void findByItem_Owner_IdAndEndIsBeforeTest() {
        List<Booking> bookings = new ArrayList<>();
        bookings.add(getBooking());
        Sort newestFirst = Sort.by(Sort.Direction.DESC, "start");
        List<Booking> bookingList = bookingRepository.findByItem_Owner_IdAndEndIsBefore(3, LocalDateTime.of(2026, 12, 12, 0, 0, 0), newestFirst);
        Assertions.assertEquals(bookings.toString(), bookingList.toString());
    }


    @Test
    void findByItem_Owner_IdAndStartIsAfterTest() {
        List<Booking> bookings = new ArrayList<>();
        bookings.add(getBooking());
        Sort newestFirst = Sort.by(Sort.Direction.DESC, "start");
        List<Booking> bookingList = bookingRepository.findByItem_Owner_IdAndStartIsAfter(3, LocalDateTime.of(2021, 12, 12, 0, 0, 0), newestFirst);
        Assertions.assertEquals(bookings.toString(), bookingList.toString());
    }

    @Test
    void findByBooker_IdAndEndIsBeforeTest() {
        List<Booking> bookings = new ArrayList<>();
        bookings.add(getBooking());
        Sort newestFirst = Sort.by(Sort.Direction.DESC, "start");
        List<Booking> bookingList = bookingRepository.findByBooker_IdAndEndIsBefore(5, LocalDateTime.of(2026, 12, 12, 0, 0, 0), newestFirst);
        Assertions.assertEquals(bookings.toString(), bookingList.toString());
    }

    @Test
    void findByBooker_IdAndStartIsAfterTest() {
        List<Booking> bookings = new ArrayList<>();
        bookings.add(getBooking());
        Sort newestFirst = Sort.by(Sort.Direction.DESC, "start");
        List<Booking> bookingList = bookingRepository.findByBooker_IdAndStartIsAfter(5, LocalDateTime.of(2021, 12, 12, 0, 0, 0), newestFirst);
        Assertions.assertEquals(bookings.toString(), bookingList.toString());
    }

    @Test
    void findByItemOwnerIdAndStartBeforeAndEndAfter() {
        List<Booking> bookings = new ArrayList<>();
        bookings.add(getBooking());
        Sort newestFirst = Sort.by(Sort.Direction.DESC, "start");
        List<Booking> bookingList = bookingRepository.findByItemOwnerIdAndStartBeforeAndEndAfter(3, LocalDateTime.of(2022, 12, 14, 0, 0, 0), newestFirst);
        Assertions.assertEquals(bookings.toString(), bookingList.toString());
    }

    @Test
    void findByBookerIdAndStartBeforeAndEndAfter() {
        List<Booking> bookings = new ArrayList<>();
        bookings.add(getBooking());
        Sort newestFirst = Sort.by(Sort.Direction.DESC, "start");
        List<Booking> bookingList = bookingRepository.findByBookerIdAndStartBeforeAndEndAfter(5, LocalDateTime.of(2022, 12, 14, 0, 0, 0), newestFirst);
        Assertions.assertEquals(bookings.toString(), bookingList.toString());
    }

    @Test
    void findByItem_Owner_IdAndStatus() {
        List<Booking> bookings = new ArrayList<>();
        bookings.add(getBooking());
        Sort newestFirst = Sort.by(Sort.Direction.DESC, "start");
        List<Booking> bookingList = bookingRepository.findByItem_Owner_IdAndStatus(3, Status.APPROVED, newestFirst);
        Assertions.assertEquals(bookings.toString(), bookingList.toString());
    }

    @Test
    void findByBooker_IdAndStatus() {
        List<Booking> bookings = new ArrayList<>();
        bookings.add(getBooking());
        Sort newestFirst = Sort.by(Sort.Direction.DESC, "start");
        List<Booking> bookingList = bookingRepository.findByBooker_IdAndStatus(5, Status.APPROVED, newestFirst);
        Assertions.assertEquals(bookings.toString(), bookingList.toString());
    }
}
