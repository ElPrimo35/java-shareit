package ru.practicum.shareit.request;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.practicum.shareit.item.dto.RequestDto;
import ru.practicum.shareit.request.dto.ItemRequestDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor(onConstructor_ = @Autowired)
@SpringBootTest
public class ItemRequestServiceTest {
    private final ItemRequestService itemRequestService;

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

    List<ItemRequestDto> getItemRequestDtos() {
        List<ItemRequestDto> itemRequestDtos = new ArrayList<>();
        ItemRequestDto itemRequestDto = new ItemRequestDto();
        itemRequestDto.setId(2);
        itemRequestDto.setDescription("Test1");
        itemRequestDto.setRequestorId(1);
        itemRequestDto.setCreated(LocalDateTime.of(2023, 12, 12, 0, 0, 0));
        itemRequestDto.setItems(null);
        itemRequestDtos.add(itemRequestDto);
        return itemRequestDtos;
    }

    @Test
    void getUserRequestsTest() {
        List<ItemRequestDto> itemRequestDtos = new ArrayList<>();
        itemRequestDtos.add(getRequestDto());
        List<ItemRequestDto> itemRequestDtoList = itemRequestService.getUserRequests(1);
        Assertions.assertEquals(itemRequestDtos, itemRequestDtoList);
    }

    @Test
    void getAllUsersRequestsTest() {
        List<ItemRequestDto> itemRequestDtoList = getItemRequestDtos();
        List<ItemRequestDto> itemRequestDtos = itemRequestService.getAllUsersRequests(1);
        Assertions.assertEquals(itemRequestDtoList, itemRequestDtos);
    }

    @Test
    void getRequestById() {
        ItemRequestDto itemRequestDtoTest = getRequestDto();
        ItemRequestDto itemRequestDto = itemRequestService.getRequestById(1, 1);
        Assertions.assertEquals(itemRequestDtoTest, itemRequestDto);
    }
}
