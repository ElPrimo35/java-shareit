package ru.practicum.shareit.request;

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
import ru.practicum.shareit.item.dto.RequestDto;
import ru.practicum.shareit.request.dto.ItemRequestDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ItemRequestController.class)
@AutoConfigureMockMvc
public class ItemRequestControllerTest {

    @MockBean
    private ItemRequestService itemRequestService;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper() {
        return new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }


    List<RequestDto> getRequestDtos() {
        List<RequestDto> requestDtoList = new ArrayList<>();
        RequestDto requestDto = new RequestDto();
        requestDto.setItemId(1);
        requestDto.setOwnerId(1);
        requestDto.setName("Test");
        requestDtoList.add(requestDto);
        return requestDtoList;
    }

    ItemRequestDto getRequestDto() {
        ItemRequestDto dto = new ItemRequestDto();
        dto.setId(1);
        dto.setDescription("Test");
        dto.setRequestorId(1);
        dto.setCreated(LocalDateTime.of(2023, 12, 12, 0, 0, 0));
        dto.setItems(getRequestDtos());
        return dto;
    }

    @Test
    void createRequestTest() throws Exception {
        Integer userId = 1;
        ItemRequestDto inputRequest = new ItemRequestDto();
        inputRequest.setDescription("Test");

        ItemRequestDto expectedRequest = getRequestDto();
        expectedRequest.setDescription(inputRequest.getDescription());

        String inputJson = objectMapper().writeValueAsString(inputRequest);

        when(itemRequestService.createRequest(any(ItemRequestDto.class), eq(userId)))
                .thenReturn(expectedRequest);

        mockMvc.perform(post("/requests")
                        .header("X-Sharer-User-Id", userId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJson))
                .andExpect(status().isOk());

        verify(itemRequestService, times(1))
                .createRequest(any(ItemRequestDto.class), eq(userId));
    }

    @Test
    void getUserRequestsTest() throws Exception {
        Integer userId = 1;

        List<ItemRequestDto> expectedRequests = List.of(
                getRequestDto(),
                getRequestDto()
        );
        expectedRequests.get(1).setId(2);

        when(itemRequestService.getUserRequests(eq(userId)))
                .thenReturn(expectedRequests);

        mockMvc.perform(get("/requests")
                        .header("X-Sharer-User-Id", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(itemRequestService, times(1)).getUserRequests(eq(userId));
    }

    @Test
    void getRequestTest() throws Exception {
        Integer requestId = 1;
        Integer userId = 1;

        ItemRequestDto expectedRequest = getRequestDto();

        when(itemRequestService.getRequestById(eq(requestId), eq(userId)))
                .thenReturn(expectedRequest);

        mockMvc.perform(get("/requests/{requestId}", requestId)
                        .header("X-Sharer-User-Id", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(itemRequestService, times(1)).getRequestById(eq(requestId), eq(userId));
    }

    @Test
    void getAllUsersRequestsTest() throws Exception {
        Integer userId = 1;

        List<ItemRequestDto> expectedRequests = List.of(
                getRequestDto(),
                getRequestDto()
        );
        expectedRequests.get(1).setId(2);
        expectedRequests.get(1).setDescription("Нужен молоток");


        when(itemRequestService.getAllUsersRequests(eq(userId)))
                .thenReturn(expectedRequests);

        mockMvc.perform(get("/requests/all")
                        .header("X-Sharer-User-Id", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(itemRequestService, times(1)).getAllUsersRequests(eq(userId));
    }
}
