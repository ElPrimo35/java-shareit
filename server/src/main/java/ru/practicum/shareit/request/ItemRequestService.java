package ru.practicum.shareit.request;

import ru.practicum.shareit.request.dto.ItemRequestDto;

import java.util.List;

public interface ItemRequestService {
    ItemRequestDto createRequest(ItemRequestDto itemRequest, Integer userId);

    List<ItemRequestDto> getUserRequests(Integer userId);

    ItemRequestDto getRequestById(Integer requestId, Integer userId);

    List<ItemRequestDto> getAllUsersRequests(Integer userId);
}
