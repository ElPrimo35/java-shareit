package ru.practicum.shareit.request;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.item.dto.RequestDto;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.user.User;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class RequestMapper {
    public ItemRequest toRequest(ItemRequestDto itemRequestDto, User requestor, LocalDateTime created) {
        ItemRequest itemRequest = new ItemRequest();
        itemRequest.setId(itemRequestDto.getId());
        itemRequest.setDescription(itemRequestDto.getDescription());
        itemRequest.setCreated(created);
        itemRequest.setRequestor(requestor);
        return itemRequest;
    }

    public ItemRequestDto toRequestDto(ItemRequest itemRequest, Integer requestorId, LocalDateTime created, List<RequestDto> requestDtoList) {
        ItemRequestDto itemRequestDto = new ItemRequestDto();
        itemRequestDto.setId(itemRequest.getId());
        itemRequestDto.setDescription(itemRequest.getDescription());
        itemRequestDto.setCreated(created);
        itemRequestDto.setRequestorId(requestorId);
        itemRequestDto.setItems(requestDtoList);
        return itemRequestDto;
    }

    public ItemRequestDto toDto(ItemRequest itemRequest, Integer requestorId, LocalDateTime created) {
        ItemRequestDto itemRequestDto = new ItemRequestDto();
        itemRequestDto.setId(itemRequest.getId());
        itemRequestDto.setDescription(itemRequest.getDescription());
        itemRequestDto.setCreated(created);
        itemRequestDto.setRequestorId(requestorId);
        return itemRequestDto;
    }
}
