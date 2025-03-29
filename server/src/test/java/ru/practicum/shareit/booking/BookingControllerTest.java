package ru.practicum.shareit.booking;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.BookingResponseDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.user.dto.UserDto;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookingController.class)
@AutoConfigureMockMvc
public class BookingControllerTest {
    @MockBean
    private BookingService bookingService;

    @Autowired
    private MockMvc mockMvc;

    UserDto getUser5() {
        UserDto user = new UserDto();
        user.setId(5);
        user.setName("Test4");
        user.setEmail("Test4@Test");
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

    BookingResponseDto getResponseBooking() {
        BookingResponseDto booking = new BookingResponseDto();
        booking.setId(1);
        booking.setStart(LocalDateTime.of(2022, 12, 12, 0, 0, 0));
        booking.setEnd(LocalDateTime.of(2023, 12, 12, 0, 0, 0));
        booking.setItem(getItem3());
        booking.setBooker(getUser5());
        booking.setStatus(Status.APPROVED);
        return booking;
    }

    BookingDto getBookingDto() {
        BookingDto bookingDto = new BookingDto();
        bookingDto.setId(1);
        bookingDto.setStart(LocalDateTime.of(2022, 12, 12, 0, 0, 0));
        bookingDto.setEnd(LocalDateTime.of(2023, 12, 12, 0, 0, 0));
        bookingDto.setItemId(3);
        return bookingDto;
    }


    private ObjectMapper objectMapper() {
        return new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    @Test
    void createBooking() throws Exception {
        BookingDto bookingDto = getBookingDto();
        BookingResponseDto bookingResponseDto = getResponseBooking();

        String bookingDtoJson = objectMapper().writeValueAsString(bookingDto);
        String responseJson = objectMapper().writeValueAsString(bookingResponseDto);

        when(bookingService.createBooking(any(BookingDto.class), eq(5))).thenReturn(bookingResponseDto);

        mockMvc.perform(post("/bookings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookingDtoJson)
                        .header("X-Sharer-User-Id", 5))
                .andExpect(status().isOk())
                .andExpect(content().json(responseJson));

        verify(bookingService, times(1)).createBooking(any(BookingDto.class), eq(5));
    }

    @Test
    void confirmationBookingTest() throws Exception {
        Integer bookingId = 1;
        Integer userId = 5;
        boolean approved = true;

        BookingResponseDto bookingResponseDto = getResponseBooking();
        String responseJson = objectMapper().writeValueAsString(bookingResponseDto);

        when(bookingService.confirmationBooking(eq(bookingId), eq(approved), eq(userId)))
                .thenReturn(bookingResponseDto);

        mockMvc.perform(patch("/bookings/{bookingId}", bookingId)
                        .header("X-Sharer-User-Id", userId)
                        .param("approved", String.valueOf(approved))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(responseJson));

        verify(bookingService, times(1)).confirmationBooking(eq(bookingId), eq(approved), eq(userId));
    }

    @Test
    void getBookingByIdTest() throws Exception {
        Integer bookingId = 1;
        Integer userId = 5;

        BookingResponseDto bookingResponseDto = getResponseBooking();
        String responseJson = objectMapper().writeValueAsString(bookingResponseDto);

        when(bookingService.getBookingById(eq(bookingId), eq(userId)))
                .thenReturn(bookingResponseDto);

        mockMvc.perform(get("/bookings/{bookingId}", bookingId)
                        .header("X-Sharer-User-Id", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(responseJson));

        verify(bookingService, times(1)).getBookingById(eq(bookingId), eq(userId));
    }

    @Test
    void getBookingsTest() throws Exception {
        Integer userId = 5;
        String stateParam = "ALL";

        List<BookingResponseDto> bookingList = List.of(
                getResponseBooking()
        );

        String responseJson = objectMapper().writeValueAsString(bookingList);

        when(bookingService.getAllBookings(eq(BookingState.ALL), eq(userId)))
                .thenReturn(bookingList);

        mockMvc.perform(get("/bookings")
                        .header("X-Sharer-User-Id", userId)
                        .param("state", stateParam)
                        .param("from", "0")
                        .param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(responseJson));

        verify(bookingService, times(1)).getAllBookings(eq(BookingState.ALL), eq(userId));
    }


    @Test
    void getOwnerBookingsTest() throws Exception {
        Integer userId = 5;
        String stateParam = "ALL";

        List<BookingResponseDto> bookingList = List.of(getResponseBooking());
        String responseJson = objectMapper().writeValueAsString(bookingList);

        when(bookingService.getOwnerBookings(eq(BookingState.ALL), eq(userId)))
                .thenReturn(bookingList);

        mockMvc.perform(get("/bookings/owner")
                        .header("X-Sharer-User-Id", userId)
                        .param("state", stateParam)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(responseJson));

        verify(bookingService, times(1)).getOwnerBookings(eq(BookingState.ALL), eq(userId));
    }

}
